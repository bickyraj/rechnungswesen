package com.rechnungswesen.app.controllers;

import com.rechnungswesen.app.controllers.request.CreateCustomerRequestDTO;
import com.rechnungswesen.app.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customer")
@Validated
public class CustomerController {
	private final CustomerService customerService;

	@PostMapping("")
	public ResponseEntity<Boolean> create(@Valid @RequestBody CreateCustomerRequestDTO requestDTO) {
		customerService.create(requestDTO);
		return ResponseEntity.ok(true);
	}
}
