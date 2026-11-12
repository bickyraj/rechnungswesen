
package com.rechnungswesen.app.exceptions;

import com.rechnungswesen.app.common.ApiResponse;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
		log.warn("Validation error: {}", ex.getMessage());

		return ResponseEntity.status(400).body(
				ApiResponse.builder().message(ex.getMessage()).build()
		);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		log.warn("Invalid argument: {}", ex.getMessage());

		return ResponseEntity.status(400).body(
				ApiResponse.builder().message(ex.getMessage()).build()
		);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiResponse> handleIllegalStateException(IllegalStateException ex) {
		log.error("Unexpected illegal state exception", ex);

		return ResponseEntity.status(400).body(
				ApiResponse.builder().message(ex.getMessage()).build()
		);
	}
}
