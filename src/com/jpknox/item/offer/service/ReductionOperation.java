package com.jpknox.item.offer.service;

import com.jpknox.item.offer.PriceDeductible;
import com.jpknox.item.offer.Reduction;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by joaok on 04/11/2018.
 */
public class ReductionOperation implements Comparable<ReductionOperation> {

	private String description;
	private Reduction reduction;
	private Function<PriceDeductible, BigDecimal> function;
	private BigDecimal reductionAmount;

	public ReductionOperation(String description, Reduction reduction, Function<PriceDeductible, BigDecimal> function) {
		this.description = description;
		this.reduction = reduction;
		this.function = function;
	}

	public String getDescription() {
		return description;
	}

	public Reduction getReduction() {
		return reduction;
	}

	public Function<PriceDeductible, BigDecimal> getFunction() {
		return function;
	}

	public BigDecimal getReductionAmount() {
		return reductionAmount;
	}

	public void setReductionAmount(BigDecimal reductionAmount) {
		this.reductionAmount = reductionAmount;
	}

	@Override
	public int compareTo(ReductionOperation o) {
		//Sort by most crucial component of this class.
		return getReduction().compareTo(o.getReduction());
	}
}
