package com.jpknox.basket.display;

import com.jpknox.item.offer.SpecialOffer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaok on 04/11/2018.
 */
public class Receipt {

	private BigDecimal subtotal;
	private List<SpecialOffer> appliedOffers = new ArrayList();
	private BigDecimal total;

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public List<SpecialOffer> getAppliedOffers() {
		return appliedOffers;
	}

	public void setAppliedOffers(List<SpecialOffer> appliedOffers) {
		this.appliedOffers = appliedOffers;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
