package com.rechnungswesen.app.customer.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {
	private final Long id;
	private final String keycloakUserId;
	private final String firstName;
	private final String lastName;
	private final String email;
}
