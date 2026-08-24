package com.rechnungswesen.app.customer.repository;

import com.rechnungswesen.app.account.model.AccountModel;
import com.rechnungswesen.app.account.repository.JpaAccountRepository;
import com.rechnungswesen.app.payment.entity.LedgerEntry;
import com.rechnungswesen.app.payment.model.LedgerEntryModel;
import com.rechnungswesen.app.payment.model.PaymentModel;
import com.rechnungswesen.app.payment.repository.JpaPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlLedgerEntryRepository implements LedgerEntryRepository {

	private final JpaLedgerEntryRepository jpaLedgerEntryRepository;
	private final JpaPaymentRepository jpaPaymentRepository;
	private final JpaAccountRepository jpaAccountRepository;
	private final ModelMapper modelMapper;

	@Override
	public void createLedgerEntry(LedgerEntry ledgerEntry) {
		LedgerEntryModel ledgerEntryModel =
				modelMapper.map(ledgerEntry, LedgerEntryModel.class);

		PaymentModel paymentModel =
				jpaPaymentRepository.getReferenceById(
						ledgerEntry.getPayment().getId()
				);

		AccountModel accountModel =
				jpaAccountRepository.getReferenceById(
						ledgerEntry.getAccount().getId()
				);

		ledgerEntryModel.setPayment(paymentModel);
		ledgerEntryModel.setAccount(accountModel);

		jpaLedgerEntryRepository.save(ledgerEntryModel);
	}
}
