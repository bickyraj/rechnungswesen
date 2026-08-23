package com.rechnungswesen.app.controllers.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequestDTO(
		@NotBlank
		String firstName,
		@NotBlank
		String lastName,
		@NotBlank
		String email
) {
}
