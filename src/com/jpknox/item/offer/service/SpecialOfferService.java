package com.jpknox.item.offer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpknox.io.property.PropertyUtil;
import com.jpknox.item.Item;
import com.jpknox.item.Purchasable;
import com.jpknox.item.offer.Criteria;
import com.jpknox.item.offer.PriceDeductible;
import com.jpknox.item.offer.Reduction;
import com.jpknox.item.offer.SpecialOffer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by joaok on 03/11/2018.
 */
public class SpecialOfferService {

	private List<SpecialOffer> offersFromProperties = null;

	public SpecialOfferService() {
		initOffers();
	}

	public List<ReductionOperation> applyReductions(List<Purchasable> items) {
		List<ReductionOperation> possibleReductions = new ArrayList();
		for (SpecialOffer offer : offersFromProperties) {
			possibleReductions.addAll(
					getApplicableReductions(items, offer)
			);
		}

		List<ReductionOperation> appliedReductions = new ArrayList();
		List<ReductionOperation> reductionsToRemove = new ArrayList();
		items.stream().filter(p -> p instanceof PriceDeductible).forEach( p -> {
			reductionsToRemove.clear();
			for (ReductionOperation reduction : possibleReductions) {
				PriceDeductible pd = (PriceDeductible) p;
				BigDecimal difference = reduction.getReduction().apply((PriceDeductible) p);
				if (difference.compareTo(BigDecimal.ZERO) > 0) {
					reduction.setReductionAmount(difference);
					appliedReductions.add(reduction);
					reductionsToRemove.add(reduction);
				}
			}
			possibleReductions.removeAll(reductionsToRemove);
		});
		return appliedReductions;
	}

	public List<ReductionOperation> getApplicableReductions(
			List<Purchasable> items, SpecialOffer offer) {
		// Multiple criteria must be checked for whole special offer.
		// Lowest number of matches dictates the number of reductions possible for this offer.
		int criteriaSatisfied[] = new int[offer.getCriterias().length];
		List<ReductionOperation> possibleReductions = new ArrayList();
		int criteriaBeingAssessed = 0;
		for (Criteria criteria : offer.getCriterias()) {
			int numberOfMatches = (int) items.stream() //Downcasting floors the floating point number
					.filter((p) -> p.getName().equalsIgnoreCase(criteria.getName()))
					.count()
					/ criteria.getQuantity();
			criteriaSatisfied[criteriaBeingAssessed++] = numberOfMatches;
		}
		Arrays.sort(criteriaSatisfied);
		if (criteriaSatisfied.length < 1 || criteriaSatisfied[0] < 1)
			return possibleReductions;

		int numberOfApplicableReductions = criteriaSatisfied[0];
		while (numberOfApplicableReductions-- > 0) {
			for (Reduction reduction : offer.getReductions()) {
				possibleReductions.add(
						new ReductionOperation(
								offer.getDescription(),
								(pd) -> {
									if (!pd.getPriceDeducted() &&
											pd.getName().equalsIgnoreCase(reduction.getName())) {
										BigDecimal difference = pd.getPrice().multiply(reduction.getAmount());
										pd.setPrice(pd.getPrice().subtract(difference));
										pd.setPriceDeducted(true);
										return difference;
									}
									return BigDecimal.ZERO;
								}
						)
				);
			}
		}
		return possibleReductions;
	}

	private void initOffers() {
		PropertyUtil propertyUtil = new PropertyUtil();
		Properties props = propertyUtil.getPropertyFile("special-offers.properties");
		ObjectMapper om = new ObjectMapper();
		try {
			SpecialOffer[] offers = om.readValue(props.getProperty("offers"), SpecialOffer[].class);
			offersFromProperties = Arrays.asList(offers);
		} catch (IOException e) {
			System.out.println("Error parsing JSON offers into POJOs.");
			e.printStackTrace();
		}

	}

}
