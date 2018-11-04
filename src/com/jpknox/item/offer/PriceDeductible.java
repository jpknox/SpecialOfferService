package com.jpknox.item.offer;

import com.jpknox.item.Purchasable;

import java.math.BigDecimal;

/**
 * Created by joaok on 03/11/2018.
 */
public interface PriceDeductible extends Purchasable {

	boolean getPriceDeducted();

	void setPriceDeducted(boolean deducted);

}
