package com.rechnungswesen.app.keycloak.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak")
public record KeycloakProperties(
		String serverUrl,
		String realm,
		String clientId,
		String adminRealm,
		String secret
) {}
