package com.rechnungswesen.app.customer.repository;

import com.rechnungswesen.app.customer.model.CustomerModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<CustomerModel, Long> {
}
