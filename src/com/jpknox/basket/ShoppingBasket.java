package com.jpknox.basket;

import com.jpknox.basket.display.Receipt;
import com.jpknox.basket.price.TotalPriceCalculator;
import com.jpknox.item.Purchasable;
import com.jpknox.item.offer.SpecialOfferService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaok on 02/11/2018.
 */
public class ShoppingBasket {

	private List<Purchasable> items = new ArrayList();
	private TotalPriceCalculator priceCalculator = new TotalPriceCalculator();
	private SpecialOfferService offerService = new SpecialOfferService();

	public void addItem(Purchasable item) {
		items.add(item);
		System.out.println(String.format("Added %s to shopping basket.", item.getName()));
	}

	public void removeItem(Purchasable item) {
		items.remove(item);
	}

	public Receipt getTotal() {
		Receipt receipt = new Receipt();
		BigDecimal subtotal = priceCalculator.calculateTotalPrice(items);
		receipt.setSubtotal(subtotal);
		offerService.applyReductions(items);
		BigDecimal total = priceCalculator.calculateTotalPrice(items);
		receipt.setTotal(total);
		return receipt;
	}

}
