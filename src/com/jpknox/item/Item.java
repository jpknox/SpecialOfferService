package com.jpknox.item;

import com.jpknox.item.offer.PriceDeductible;

import java.math.BigDecimal;

/**
 * Created by joaok on 03/11/2018.
 */
public class Item implements Purchasable, PriceDeductible {

	private BigDecimal price;
	private String name;
	private String offerCategory;
	private boolean priceDeducted;

	public Item(BigDecimal price, String name, String offerCategory) {
		this.price = price;
		this.name = name;
		this.offerCategory = offerCategory;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getOfferCategory() {
		return offerCategory;
	}

	@Override
	public void deductFromPrice(BigDecimal deduction) {
		price = price.subtract(deduction).setScale(2, BigDecimal.ROUND_UP);
		if (price.compareTo(BigDecimal.ZERO) < 0) {
			price = BigDecimal.ZERO;
		}
	}

	@Override
	public boolean getPriceDeducted() {
		return priceDeducted;
	}

	@Override
	public void setPriceDeducted(boolean deducted) {
		this.priceDeducted = deducted;
	}
}
