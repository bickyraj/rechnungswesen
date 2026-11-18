package com.rechnungswesen.app.discount;

import org.springframework.stereotype.Component;

@Component("fixed")
public class FixedDiscountStrategy implements DiscountStrategy {

	@Override
	public DiscountStrategyEnum getType() {
		return DiscountStrategyEnum.FIXED;
	}

	@Override
	public Double calculateDiscount(Double price) {
		return price - 20;
	}
}
