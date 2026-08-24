package com.rechnungswesen.app.services;

import java.math.BigDecimal;

import com.rechnungswesen.app.account.entity.Account;
import com.rechnungswesen.app.account.repository.AccountRepository;
import com.rechnungswesen.app.common.constants.AccountStatus;
import com.rechnungswesen.app.customer.entity.Customer;
import com.rechnungswesen.app.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;

	@Transactional
	public void createAccount(Long customerId, String currency) {
		Customer customer = Customer.builder().id(customerId).build();

		Account account = Account.builder()
				.customer(customer)
				.currency(currency)
				.balance(BigDecimal.ZERO)
				.status(AccountStatus.ACTIVE)
				.build();
		accountRepository.createAccount(account);
	}
}
