package com.rechnungswesen.app.payment.repository;

import java.util.Optional;
import java.util.UUID;

import com.rechnungswesen.app.payment.model.PaymentModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<PaymentModel, UUID> {
	Optional<PaymentModel> findByIdempotencyKey(String idempotencyKey);
}
