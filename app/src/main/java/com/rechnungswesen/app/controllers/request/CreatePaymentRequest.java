package com.rechnungswesen.app.controllers.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreatePaymentRequest(

		@NotNull
		Long sourceAccountId,

		@NotNull
		Long destinationAccountId,

		@NotNull
		@DecimalMin(value = "0.01")
		BigDecimal amount,

		@NotBlank
		@Pattern(
				regexp = "^[A-Z]{3}$",
				message = "Currency must be a 3-letter ISO code"
		)
		String currency,

		@NotBlank
		String idempotencyKey
) {
}
