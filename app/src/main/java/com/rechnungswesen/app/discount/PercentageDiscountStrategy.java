package com.rechnungswesen.app.discount;

import org.springframework.stereotype.Component;

@Component("percentage")
public class PercentageDiscountStrategy implements DiscountStrategy {
	@Override
	public DiscountStrategyEnum getType() {
		return DiscountStrategyEnum.PERCENTAGE;
	}

	@Override
	public Double calculateDiscount(Double price) {
		return price - (price / 100.0) * 10;
	}
}
