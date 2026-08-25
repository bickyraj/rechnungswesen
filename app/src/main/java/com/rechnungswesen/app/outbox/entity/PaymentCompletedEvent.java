package com.rechnungswesen.app.outbox.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentCompletedEvent(
		UUID paymentId,
		Long sourceAccountId,
		Long destinationAccountId,
		BigDecimal amount,
		String currency,
		Instant completedAt
) {
}
