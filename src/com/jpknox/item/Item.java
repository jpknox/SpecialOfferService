package com.jpknox.item;

import com.jpknox.item.offer.PriceDeductible;

import java.math.BigDecimal;

/**
 * Created by joaok on 03/11/2018.
 */
public class Item implements Purchasable, PriceDeductible {

	private String name;
	private BigDecimal price;
	private boolean priceDeducted;

	public Item() {
	}

	public Item(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public boolean getPriceDeducted() {
		return priceDeducted;
	}

	@Override
	public void setPriceDeducted(boolean deducted) {
		this.priceDeducted = deducted;
	}

	public Item cloneOf() {
		Item clone = new Item();
		clone.setName(getName());
		clone.setPrice(getPrice());
		return clone;
	}
}
