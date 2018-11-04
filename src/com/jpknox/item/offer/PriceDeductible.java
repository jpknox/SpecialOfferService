package com.jpknox.item.offer;

import com.jpknox.item.Purchasable;

import java.math.BigDecimal;

/**
 * Created by joaok on 03/11/2018.
 */
public interface PriceDeductible extends Purchasable {

	void deductFromPrice(BigDecimal deduction);

	boolean getPriceDeducted();

	void setPriceDeducted(boolean deducted);

	String getOfferCategory();

}
