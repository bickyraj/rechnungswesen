package com.rechnungswesen.app.account.entity;

import java.math.BigDecimal;

import com.rechnungswesen.app.common.constants.AccountStatus;
import com.rechnungswesen.app.customer.entity.Customer;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class Account {
	private final Customer customer;
	private final String currency;
	private final BigDecimal balance;
	private final AccountStatus status;
}
