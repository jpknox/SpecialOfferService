package com.jpknox.basket.display;

import com.jpknox.item.offer.service.ReductionOperation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaok on 04/11/2018.
 */
public class Receipt {

	private BigDecimal subtotal;
	private List<ReductionOperation> appliedOffers = new ArrayList();
	private BigDecimal total;

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public List<ReductionOperation> getAppliedOffers() {
		return appliedOffers;
	}

	public void setAppliedOffers(List<ReductionOperation> appliedOffers) {
		this.appliedOffers = appliedOffers;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Subtotal:\t%s\n", subtotal.setScale(2)));
		if(appliedOffers.size() == 0) {
			sb.append(String.format("(No offers available)\n"));
		} else {
			appliedOffers.stream().forEach(o -> {
				sb.append(String.format("%s: -%s\n", o.getDescription(), o.getReductionAmount().setScale(2)));
			});
		}
		sb.append(String.format("Total:\t%s\n", total.setScale(2)));
		return sb.toString();
	}
}
