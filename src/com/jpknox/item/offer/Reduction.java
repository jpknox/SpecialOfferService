package com.jpknox.item.offer;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by joaok on 04/11/2018.
 */
public class Reduction implements Comparable<Reduction> {

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

	@Override
	public int compareTo(Reduction o) {
		int i;
		i = getName().compareTo(o.getName());
		if (i != 0)
			return i;
		i = getAmount().compareTo(o.getAmount());
		return i;
	}
}
