package com.jpknox.item.offer.service;

import com.jpknox.item.offer.PriceDeductible;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created by joaok on 04/11/2018.
 */
public class ReductionOperation {

	private String description;
	private Function<PriceDeductible, BigDecimal> reduction;
	private BigDecimal reductionAmount;

	public ReductionOperation(String description, Function<PriceDeductible, BigDecimal> reduction) {
		this.description = description;
		this.reduction = reduction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Function<PriceDeductible, BigDecimal> getReduction() {
		return reduction;
	}

	public void setReduction(Function<PriceDeductible, BigDecimal> reduction) {
		this.reduction = reduction;
	}

	public BigDecimal getReductionAmount() {
		return reductionAmount;
	}

	public void setReductionAmount(BigDecimal reductionAmount) {
		this.reductionAmount = reductionAmount;
	}


}
