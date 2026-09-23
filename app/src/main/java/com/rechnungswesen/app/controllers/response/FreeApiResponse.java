package com.rechnungswesen.app.controllers.response;

public record FreeApiResponse(
		int id,
		String emoji,
		String title,
		String description,
		String documentation,
		String methods,
		String health,
		int popularity,
		Double avgReliability,
		Double avgError,
		Double avgLatency,
		String source
) {
}
