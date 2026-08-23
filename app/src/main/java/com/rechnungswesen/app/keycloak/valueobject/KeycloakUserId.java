package com.rechnungswesen.app.keycloak.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class KeycloakUserId {
	private String value;
}
