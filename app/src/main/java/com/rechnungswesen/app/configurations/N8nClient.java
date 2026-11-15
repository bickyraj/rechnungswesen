package com.rechnungswesen.app.configurations;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class N8nClient {

	private final RestClient restClient;

	public N8nClient(RestClient.Builder builder,
					 N8nConfigurationProperties properties) {
		this.restClient = builder
				.baseUrl(properties.getUrl())
				.build();
	}

	public void sendLog(Object n8nlog) {
		try {
			restClient.post()
					.body(n8nlog)
					.retrieve()
					.toBodilessEntity();
		} catch (Exception ex) {
			log.error("This is a test error for ELK");
		}
	}
}
