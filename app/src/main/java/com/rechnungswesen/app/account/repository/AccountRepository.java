package com.rechnungswesen.app.account.repository;

import java.util.Optional;

import com.rechnungswesen.app.account.entity.Account;

public interface AccountRepository {
	void createAccount(Account account);
	void updateBalance(Account account);
	Optional<Account> getByIdForUpdate(Long id);
}
