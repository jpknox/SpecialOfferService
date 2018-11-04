package com.jpknox.item.offer;

/**
 * Created by joaok on 04/11/2018.
 */
public class SpecialOffer {

	private String description;
	private Criteria[] criterias;
	private Reduction[] reductions;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Criteria[] getCriterias() {
		return criterias;
	}

	public void setCriterias(Criteria[] criterias) {
		this.criterias = criterias;
	}

	public Reduction[] getReductions() {
		return reductions;
	}

	public void setReductions(Reduction[] reductions) {
		this.reductions = reductions;
	}
}
