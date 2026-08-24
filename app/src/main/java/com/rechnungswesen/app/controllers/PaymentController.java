package com.rechnungswesen.app.controllers;

import com.rechnungswesen.app.controllers.request.CreatePaymentRequest;
import com.rechnungswesen.app.services.PaymentService;
import com.rechnungswesen.app.valueobject.IdempotencyKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createPayment(
			@Valid @RequestBody CreatePaymentRequest request
	) {

		paymentService.paymentTransfer(
				request.sourceAccountId(),
				request.destinationAccountId(),
				request.amount(),
				request.currency(),
				IdempotencyKey.of(request.idempotencyKey())
		);
	}
}
