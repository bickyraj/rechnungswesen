package com.rechnungswesen.app.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	private Long id;
	private String keycloakUserId;
	private String firstName;
	private String lastName;
	private String email;
}
