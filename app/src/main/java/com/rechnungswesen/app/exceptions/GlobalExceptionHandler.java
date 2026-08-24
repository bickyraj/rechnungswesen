package com.rechnungswesen.app.exceptions;

import com.rechnungswesen.app.common.ApiResponse;
import jakarta.validation.ValidationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
		return ResponseEntity.status(400).body(
				ApiResponse.builder().message(ex.getMessage()).build()
		);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(400).body(
				ApiResponse.builder().message(ex.getMessage()).build()
		);
	}
}
