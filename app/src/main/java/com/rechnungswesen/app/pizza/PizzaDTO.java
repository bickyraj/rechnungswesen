package com.rechnungswesen.app.pizza;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class PizzaDTO {
	private final String name;
	private final Double price;
	private final List<String> ingredients;
}
