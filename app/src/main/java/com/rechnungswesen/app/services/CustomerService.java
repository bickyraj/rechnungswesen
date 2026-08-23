package com.rechnungswesen.app.services;

import java.security.SecureRandom;
import java.util.UUID;

import com.rechnungswesen.app.controllers.request.CreateCustomerRequestDTO;
import com.rechnungswesen.app.customer.entity.Customer;
import com.rechnungswesen.app.customer.repository.CustomerRepository;
import com.rechnungswesen.app.keycloak.KeycloakService;
import com.rechnungswesen.app.keycloak.entity.KeycloakUser;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final KeycloakService keycloakService;

	private String generateUsername() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder username = new StringBuilder(5);

		SecureRandom random = new SecureRandom();

		for (int i = 0; i < 5; i++) {
			username.append(chars.charAt(random.nextInt(chars.length())));
		}

		return username.toString();
	}

	public void create(CreateCustomerRequestDTO customerRequestDTO) {
		KeycloakUser keycloakUser = KeycloakUser.builder()
				.firstName(customerRequestDTO.firstName())
				.lastName(customerRequestDTO.lastName())
				.email(customerRequestDTO.email())
				.username(generateUsername())
				.build();
		String keycloakUserId = keycloakService.createUser(keycloakUser);
		Customer customer = Customer.builder()
				.keycloakUserId(keycloakUserId)
				.firstName(customerRequestDTO.firstName())
				.lastName(customerRequestDTO.lastName())
				.email(customerRequestDTO.email())
				.build();
		customerRepository.create(customer);
	}
}
