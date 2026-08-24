package com.rechnungswesen.app.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rechnungswesen.app.valueobject.FieldError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiResponse {

	private boolean status;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<FieldError> validationErrors;
}