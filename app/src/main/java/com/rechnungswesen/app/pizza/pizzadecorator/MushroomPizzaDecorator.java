package com.rechnungswesen.app.pizza.pizzadecorator;

import java.util.ArrayList;
import java.util.List;

import com.rechnungswesen.app.pizza.Pizza;

public class MushroomPizzaDecorator extends  PizzaDecorator {
	public MushroomPizzaDecorator(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getName() {
		return "Mushroom " + this.pizza.getName();
	}

	@Override
	public Double getPrice() {
		return 300D + this.pizza.getPrice();
	}

	@Override
	public List<String> getIngredients() {
		List<String> ingredients = new ArrayList<>(this.pizza.getIngredients());
		ingredients.add("mushroom");
		return ingredients;
	}
}
