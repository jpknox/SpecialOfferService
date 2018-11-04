package com.jpknox.item.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpknox.io.property.PropertyUtil;
import com.jpknox.item.Purchasable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

/**
 * Created by joaok on 03/11/2018.
 */
public class SpecialOfferService {

	private List<SpecialOffer> offersFromProperties = null;

	public SpecialOfferService() {
		initOffers();
	}

	public void applyReductions(List<Purchasable> items) {
		List<Function<PriceDeductible, Boolean>> possibleReductions = new ArrayList();
		for (SpecialOffer offer : offersFromProperties) {
			possibleReductions.addAll(
					getApplicableReductions(items, offer)
			);
		}

		List<Function<PriceDeductible, Boolean>> appliedReductions = new ArrayList();
		List<Function<PriceDeductible, Boolean>> reductionsToRemove = new ArrayList();
		items.stream().filter(p -> p instanceof PriceDeductible).forEach(p -> {
			reductionsToRemove.clear();
			for (Function<PriceDeductible, Boolean> reduction : possibleReductions) {
				PriceDeductible pd = (PriceDeductible) p;
				boolean applied = reduction.apply((PriceDeductible) p);
				if (applied) {
					appliedReductions.add(reduction);
					reductionsToRemove.add(reduction);
				}
			}
			possibleReductions.removeAll(reductionsToRemove);
		});
	}

	public List<Function<PriceDeductible, Boolean>> getApplicableReductions(
			List<Purchasable> items, SpecialOffer offer) {
		// Multiple criteria must be checked for whole special offer.
		// Lowest number of matches dictates the number of reductions possible for this offer.
		int criteriaSatisfied[] = new int[offer.getCriterias().length];
		List<Function<PriceDeductible, Boolean>> possibleReductions = new ArrayList();
		int criteriaBeingAssessed = 0;
		for (Criteria criteria : offer.getCriterias()) {
			int numberOfMatches = (int) items.stream()
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
						(pd) -> {
							if (!pd.getPriceDeducted() &&
									pd.getName().equalsIgnoreCase(reduction.getName())) {
								pd.setPrice(
										pd.getPrice().subtract(
												pd.getPrice().multiply(reduction.getAmount())));
								pd.setPriceDeducted(true);
								return true;
							}
							return false;
						}
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
