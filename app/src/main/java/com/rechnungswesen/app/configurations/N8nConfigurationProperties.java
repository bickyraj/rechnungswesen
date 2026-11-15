package com.rechnungswesen.app.configurations;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "n8n")
public class N8nConfigurationProperties {

	private String url;
}
