package com.rechnungswesen.app.pizza.pizzadecorator;

import java.util.ArrayList;
import java.util.List;

import com.rechnungswesen.app.pizza.Pizza;

public class OlivePizzaDecorator extends PizzaDecorator {
	public OlivePizzaDecorator(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getName() {
		return "Olive " + this.pizza.getName();
	}

	@Override
	public Double getPrice() {
		return 900D + this.pizza.getPrice();
	}

	@Override
	public List<String> getIngredients() {
		List<String> ingredients = new ArrayList<>(this.pizza.getIngredients());
		ingredients.add("olive");
		return ingredients;
	}
}
