package com.rechnungswesen.app.messaging.event;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentCompletedEvent(
		UUID paymentId,
		Long sourceAccountId,
		Long destinationAccountId,
		BigDecimal amount,
		String currency
) {
}
