package com.rechnungswesen.app.payment.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.rechnungswesen.app.account.entity.Account;
import com.rechnungswesen.app.common.constants.PaymentStatus;

public class Payment {
	private UUID id;
	private Account sourceAccount;
	private Account destinationAccount;
	private BigDecimal amount;
	private String currency;
	private PaymentStatus status;
	private String idempotencyKey;
	private Instant createdAt;
	private Instant completedAt;
}
