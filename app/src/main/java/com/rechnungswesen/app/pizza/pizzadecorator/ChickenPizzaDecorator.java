package com.rechnungswesen.app.pizza.pizzadecorator;

import java.util.ArrayList;
import java.util.List;

import com.rechnungswesen.app.pizza.Pizza;

public class ChickenPizzaDecorator extends PizzaDecorator {
	public ChickenPizzaDecorator(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getName() {
		return "Chicken " + this.pizza.getName();
	}

	@Override
	public Double getPrice() {
		return 900D + this.pizza.getPrice();
	}

	@Override
	public List<String> getIngredients() {
		List<String> ingredients = new ArrayList<>(this.pizza.getIngredients());
		ingredients.add("chicken");
		return ingredients;
	}
}
