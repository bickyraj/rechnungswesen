package com.rechnungswesen.app.valueobject;

public record FieldError(
		String field,
		String message
) {}
