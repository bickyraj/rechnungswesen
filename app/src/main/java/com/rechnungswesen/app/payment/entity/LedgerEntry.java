package com.rechnungswesen.app.payment.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.rechnungswesen.app.account.entity.Account;
import com.rechnungswesen.app.common.constants.LedgerEntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LedgerEntry {

	private UUID id;

	private Payment payment;

	private Account account;

	private LedgerEntryType entryType;

	private BigDecimal amount;

	private String currency;

	private Instant createdAt;
}
