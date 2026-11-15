package com.rechnungswesen.app.services;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.rechnungswesen.app.configurations.N8nClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class N8nLogService {

	private final N8nClient n8nClient;

	public void sendError(String message) {
		Map<String, Object> log = Map.of(
				"level", "ERROR",
				"message", message,
				"application", "springboot"
		);
		n8nClient.sendLog(log);
	}
}
