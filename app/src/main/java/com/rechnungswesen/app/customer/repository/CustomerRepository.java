package com.rechnungswesen.app.customer.repository;

import java.util.Optional;

import com.rechnungswesen.app.customer.entity.Customer;

public interface CustomerRepository {
	void create(Customer customer);
	Optional<Customer> getById(Long id);
}
