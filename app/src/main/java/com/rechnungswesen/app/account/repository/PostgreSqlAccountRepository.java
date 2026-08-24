package com.rechnungswesen.app.account.repository;

import java.util.Optional;

import com.rechnungswesen.app.account.entity.Account;
import com.rechnungswesen.app.account.model.AccountModel;
import com.rechnungswesen.app.customer.model.CustomerModel;
import com.rechnungswesen.app.customer.repository.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlAccountRepository implements AccountRepository {
	private final JpaAccountRepository jpaAccountRepository;
	private final JpaCustomerRepository jpaCustomerRepository;
	private final ModelMapper modelMapper;

	@Override
	public void createAccount(Account account) {
		CustomerModel customerModel = jpaCustomerRepository
				.findById(account.getCustomer().getId())
				.orElseThrow(() ->
						new IllegalArgumentException("Customer not found"));

		if (jpaAccountRepository.existsByCustomerIdAndCurrency(account.getCustomer().getId(), account.getCurrency())) {
			throw new IllegalArgumentException(
					"Customer already has an account in " + account.getCurrency()
			);
		}

		AccountModel accountModel =
				modelMapper.map(account, AccountModel.class);
		accountModel.setCustomer(customerModel);
		jpaAccountRepository.save(accountModel);
	}

	@Override
	public void updateBalance(Account account) {
		AccountModel accountModel =
				jpaAccountRepository.findById(account.getId())
						.orElseThrow(() ->
								new IllegalArgumentException("Account not found"));

		accountModel.setBalance(account.getBalance());

		jpaAccountRepository.save(accountModel);
	}

	@Override
	public Optional<Account> getByIdForUpdate(Long id) {
		return jpaAccountRepository.findByIdForUpdate(id)
				.map(accountModel -> modelMapper.map(accountModel, Account.class));
	}
}
