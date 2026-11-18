package com.rechnungswesen.app.discount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {

	private final Map<DiscountStrategyEnum, DiscountStrategy> strategies = new HashMap<>();

	public DiscountService(List<DiscountStrategy> strategies) {
		strategies.forEach(str -> {
			this.strategies.put(str.getType(), str);
		});
	}

	public Double calcFinalPrice(Double price, DiscountStrategyEnum strategy) {
		return this.strategies.get(strategy).calculateDiscount(price);
	}
}
