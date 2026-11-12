package com.rechnungswesen.app.account.entity;

import java.math.BigDecimal;

import com.rechnungswesen.app.common.constants.AccountStatus;
import com.rechnungswesen.app.common.constants.AccountType;
import com.rechnungswesen.app.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	private Long id;
	private Customer customer;
	private String currency;
	private BigDecimal balance;
	private AccountStatus status;
	private AccountType type;
}