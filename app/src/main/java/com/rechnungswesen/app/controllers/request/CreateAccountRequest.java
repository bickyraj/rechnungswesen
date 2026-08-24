package com.rechnungswesen.app.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateAccountRequest(
		@NotNull(message = "Customer ID is required")
		Long customerId,
		@NotBlank(message = "Currency is required")
		@Pattern(
				regexp = "^[A-Z]{3}$",
				message = "Currency must be a 3-letter ISO code"
		)
		String currency
) {
}
