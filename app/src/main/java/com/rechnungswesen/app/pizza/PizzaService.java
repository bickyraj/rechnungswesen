package com.rechnungswesen.app.pizza;

import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	public PizzaDTO bakePizza(Pizza pizza) {
		return PizzaDTO.builder()
				.name(pizza.getName())
				.price(pizza.getPrice())
				.ingredients(pizza.getIngredients())
				.build();
	}
}
