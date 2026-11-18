package com.rechnungswesen.app.discount;

import org.springframework.stereotype.Component;

@Component("no")
public class NoDiscountStrategy implements DiscountStrategy {
	@Override
	public DiscountStrategyEnum getType() {
		return DiscountStrategyEnum.NO;
	}

	@Override
	public Double calculateDiscount(Double price) {
		return price;
	}
}
