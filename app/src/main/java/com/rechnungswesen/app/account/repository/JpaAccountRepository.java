package com.rechnungswesen.app.account.repository;

import com.rechnungswesen.app.account.model.AccountModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountModel, Long> {
	boolean existsByCustomerIdAndCurrency(Long customerId, String currency);
}
