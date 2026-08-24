package com.rechnungswesen.app.payment.repository;

import java.util.Optional;

import com.rechnungswesen.app.payment.entity.Payment;
import com.rechnungswesen.app.valueobject.IdempotencyKey;

public interface PaymentRepository {
	Payment savePayment(Payment payment);
	Optional<Payment> findByIdempotencyKey(IdempotencyKey idempotencyKey);
}
