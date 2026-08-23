package com.rechnungswesen.app.keycloak.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class KeycloakUser {

	private final String username;
	private final String firstName;
	private final String lastName;
	private final String email;
}
