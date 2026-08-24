package com.rechnungswesen.app.payment.repository;

import java.util.Optional;

import com.rechnungswesen.app.account.model.AccountModel;
import com.rechnungswesen.app.account.repository.JpaAccountRepository;
import com.rechnungswesen.app.payment.entity.Payment;
import com.rechnungswesen.app.payment.model.PaymentModel;
import com.rechnungswesen.app.valueobject.IdempotencyKey;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlPaymentRepository implements PaymentRepository {
	private final JpaPaymentRepository jpaPaymentRepository;
	private final JpaAccountRepository jpaAccountRepository;
	private final ModelMapper modelMapper;

	@Override
	public Payment savePayment(Payment payment) {
		PaymentModel paymentModel =
				modelMapper.map(payment, PaymentModel.class);

		AccountModel sourceAccount =
				jpaAccountRepository.getReferenceById(
						payment.getSourceAccount().getId()
				);

		AccountModel destinationAccount =
				jpaAccountRepository.getReferenceById(
						payment.getDestinationAccount().getId()
				);

		paymentModel.setSourceAccount(sourceAccount);
		paymentModel.setDestinationAccount(destinationAccount);
		paymentModel.setIdempotencyKey(payment.getIdempotencyKey().getValue());

		PaymentModel savedModel = jpaPaymentRepository.save(paymentModel);
		payment.setId(savedModel.getId());
		return  payment;
	}

	@Override
	public Optional<Payment> findByIdempotencyKey(IdempotencyKey idempotencyKey) {
		return jpaPaymentRepository.findByIdempotencyKey(idempotencyKey.getValue())
				.map(paymentModel -> modelMapper.map(paymentModel, Payment.class));
	}
}
