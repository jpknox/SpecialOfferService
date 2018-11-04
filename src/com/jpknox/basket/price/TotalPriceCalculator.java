package com.jpknox.basket.price;

import com.jpknox.item.Purchasable;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by joaok on 02/11/2018.
 */
public class TotalPriceCalculator {

	public BigDecimal calculateTotalPrice(List<Purchasable> items) {
		BigDecimal total = BigDecimal.ZERO;
		for(Purchasable item : items) {
			total = total.add(item.getPrice());
		}
		return total;
	}

}
