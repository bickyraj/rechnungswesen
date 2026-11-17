package com.rechnungswesen.app.pizza.pizzadecorator;

import com.rechnungswesen.app.pizza.Pizza;

abstract class PizzaDecorator implements Pizza {

	protected Pizza pizza;
	public PizzaDecorator(Pizza pizza) {
		this.pizza = pizza;
	}
}
