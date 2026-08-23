package com.rechnungswesen.app.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class AppController {

	@GetMapping("/home")
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("welcome to accounting.");
	}
}
