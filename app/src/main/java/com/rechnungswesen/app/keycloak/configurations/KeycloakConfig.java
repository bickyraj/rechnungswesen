package com.rechnungswesen.app.keycloak.configurations;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KeycloakProperties.class)
public class KeycloakConfig {

	@Bean
	public Keycloak keycloak(KeycloakProperties keycloakProperties) {
		return KeycloakBuilder.builder()
				.serverUrl(keycloakProperties.serverUrl())
				.realm(keycloakProperties.realm())
				.clientId(keycloakProperties.clientId())
				.clientSecret(keycloakProperties.secret())
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
				.build();
	}
}
