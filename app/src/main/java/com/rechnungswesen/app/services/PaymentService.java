package com.rechnungswesen.app.services;

import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rechnungswesen.app.account.entity.Account;
import com.rechnungswesen.app.account.repository.AccountRepository;
import com.rechnungswesen.app.common.constants.LedgerEntryType;
import com.rechnungswesen.app.common.constants.PaymentStatus;
import com.rechnungswesen.app.customer.repository.LedgerEntryRepository;
import com.rechnungswesen.app.outbox.entity.OutboxEvent;
import com.rechnungswesen.app.outbox.entity.PaymentCompletedEvent;
import com.rechnungswesen.app.outbox.repository.OutboxRepository;
import com.rechnungswesen.app.payment.entity.LedgerEntry;
import com.rechnungswesen.app.payment.entity.Payment;
import com.rechnungswesen.app.payment.repository.PaymentRepository;
import com.rechnungswesen.app.valueobject.IdempotencyKey;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final AccountRepository accountRepository;
	private final LedgerEntryRepository ledgerEntryRepository;
	private final OutboxRepository outboxRepository;
	private final ModelMapper modelMapper;
	private final ObjectMapper objectMapper;

	@Transactional
	public void paymentTransfer(
			Long sourceAccountId,
			Long destinationAccountId,
			BigDecimal amount,
			String currency,
			IdempotencyKey idempotencyKey
	) {

		if (sourceAccountId.equals(destinationAccountId)) {
			throw new IllegalArgumentException(
					"Source and destination accounts must be different"
			);
		}

		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException(
					"Amount must be greater than zero"
			);
		}

		if (paymentRepository.findByIdempotencyKey(idempotencyKey).isPresent()) {
			return;
		}

		Account sourceAccount = accountRepository.getByIdForUpdate(sourceAccountId)
				.orElseThrow(() -> new IllegalArgumentException("Source account not found."));

		Account destinationAccount = accountRepository.getByIdForUpdate(destinationAccountId)
				.orElseThrow(() -> new IllegalArgumentException("Destination account not found."));


		if (!sourceAccount.getCurrency().equals(currency)
				|| !destinationAccount.getCurrency().equals(currency)) {
			throw new IllegalArgumentException(
					"Currency does not match account currency"
			);
		}

		if (sourceAccount.getBalance().compareTo(amount) < 0) {
			throw new IllegalArgumentException(
					"Insufficient funds"
			);
		}

		Payment payment = Payment.builder()
				.sourceAccount(sourceAccount)
				.destinationAccount(destinationAccount)
				.amount(amount)
				.currency(currency)
				.status(PaymentStatus.PENDING)
				.idempotencyKey(idempotencyKey)
				.createdAt(Instant.now())
				.build();
		Payment savedPayment = paymentRepository.savePayment(payment);

		// ledger entry
		sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
		destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
		accountRepository.updateBalance(sourceAccount);
		accountRepository.updateBalance(destinationAccount);

		LedgerEntry debit = LedgerEntry.builder()
				.payment(savedPayment)
				.account(sourceAccount)
				.entryType(LedgerEntryType.DEBIT)
				.amount(amount)
				.currency(currency)
				.createdAt(Instant.now())
				.build();

		LedgerEntry credit = LedgerEntry.builder()
				.payment(savedPayment)
				.account(destinationAccount)
				.entryType(LedgerEntryType.CREDIT)
				.amount(amount)
				.currency(currency)
				.createdAt(Instant.now())
				.build();

		ledgerEntryRepository.createLedgerEntry(debit);
		ledgerEntryRepository.createLedgerEntry(credit);

		savedPayment.setStatus(PaymentStatus.COMPLETED);
		savedPayment.setCompletedAt(Instant.now());
		paymentRepository.savePayment(savedPayment);

		// NEW: create outbox event
		PaymentCompletedEvent eventPayload =
				new PaymentCompletedEvent(
						savedPayment.getId(),
						sourceAccount.getId(),
						destinationAccount.getId(),
						savedPayment.getAmount(),
						savedPayment.getCurrency(),
						savedPayment.getCompletedAt()
				);

		String payload;
		try {
			payload = objectMapper.writeValueAsString(eventPayload);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(
					"Failed to serialize payment event", e
			);
		}
		OutboxEvent event = OutboxEvent.builder()
				.eventType("PAYMENT_COMPLETED")
				.aggregateId(savedPayment.getId())
				.payload(payload)
				.createdAt(Instant.now())
				.build();

		outboxRepository.save(event);
	}
}
