package com.rechnungswesen.app.pizza;

abstract class BasePizza implements Pizza {

	void displayPrice() {
		System.out.println(this.getIngredients());
	}
}
