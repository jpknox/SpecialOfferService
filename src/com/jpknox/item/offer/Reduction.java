package com.jpknox.item.offer;

import java.math.BigDecimal;

/**
 * Created by joaok on 04/11/2018.
 */
public class Reduction {

	private String name;
	private BigDecimal amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
