package com.rechnungswesen.app.payment.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.rechnungswesen.app.account.entity.Account;
import com.rechnungswesen.app.common.constants.PaymentStatus;
import com.rechnungswesen.app.valueobject.IdempotencyKey;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Payment {
	private UUID id;
	private final Account sourceAccount;
	private final Account destinationAccount;
	private final BigDecimal amount;
	private final String currency;
	private PaymentStatus status;
	private final IdempotencyKey idempotencyKey;
	private final Instant createdAt;
	private Instant completedAt;
}
