package com.rechnungswesen.app.keycloak;

import java.util.List;

import com.rechnungswesen.app.keycloak.configurations.KeycloakProperties;
import com.rechnungswesen.app.keycloak.entity.KeycloakUser;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {
	private final Keycloak keycloak;
	private final KeycloakProperties keycloakProperties;

	public String createUser(KeycloakUser keycloakUser) {
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setEmailVerified(true);
		userRepresentation.setFirstName(keycloakUser.getFirstName());
		userRepresentation.setLastName(keycloakUser.getLastName());
		userRepresentation.setEmail(keycloakUser.getEmail());
		userRepresentation.setUsername(keycloakUser.getUsername());
		userRepresentation.setEnabled(true);
		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
		credentialRepresentation.setTemporary(false);
		credentialRepresentation.setValue("test");
		userRepresentation.setCredentials(List.of(credentialRepresentation));

		try (Response response = keycloak
				.realm(keycloakProperties.realm())
				.users()
				.create(userRepresentation)) {
			if (response.getStatus() == 409) {
				throw new ValidationException("email already exists");
			}
			return CreatedResponseUtil.getCreatedId(response);
		}
	}
}
