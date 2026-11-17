package com.rechnungswesen.app.pizza;

import java.util.ArrayList;
import java.util.List;

public class PlainPizza extends BasePizza {
	@Override
	public String getName() {
		return "Plain Pizza";
	}

	@Override
	public Double getPrice() {
		return 100.0;
	}

	@Override
	public List<String> getIngredients() {
		return List.of("cheese", "tomato");
	}
}
