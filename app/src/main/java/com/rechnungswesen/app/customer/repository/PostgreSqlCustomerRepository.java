package com.rechnungswesen.app.customer.repository;

import java.util.Optional;

import com.rechnungswesen.app.customer.entity.Customer;
import com.rechnungswesen.app.customer.model.CustomerModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlCustomerRepository implements CustomerRepository {

	private final JpaCustomerRepository jpaCustomerRepository;
	private final ModelMapper modelMapper;

	@Override
	public void create(Customer customer) {
		CustomerModel customerModel = modelMapper.map(customer, CustomerModel.class);
		jpaCustomerRepository.save(customerModel);
	}

	@Override
	public Optional<Customer> getById(Long id) {
		return Optional.empty();
	}
}
