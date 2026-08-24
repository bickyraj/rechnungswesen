package com.rechnungswesen.app.controllers;

import com.rechnungswesen.app.controllers.request.CreateAccountRequest;
import com.rechnungswesen.app.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createAccount(
			@Valid @RequestBody CreateAccountRequest request
	) {
		accountService.createAccount(
				request.customerId(),
				request.currency()
		);
	}
}
