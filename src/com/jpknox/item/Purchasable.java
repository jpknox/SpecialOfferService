package com.jpknox.item;

import java.math.BigDecimal;

/**
 * Created by joaok on 02/11/2018.
 */
public interface Purchasable {

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	String getName();
}
