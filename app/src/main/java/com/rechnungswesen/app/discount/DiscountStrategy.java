package com.rechnungswesen.app.discount;

public interface DiscountStrategy {
	DiscountStrategyEnum getType();
	Double calculateDiscount(Double price);
}
