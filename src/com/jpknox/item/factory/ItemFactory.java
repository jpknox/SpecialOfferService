package com.jpknox.item.factory;

import com.jpknox.item.Item;

import java.math.BigDecimal;

/**
 * Created by joaok on 03/11/2018.
 */
public class ItemFactory {

	public Item create(String name) {
		switch (name.toUpperCase()) {
			case "JACKET": return new Item(BigDecimal.valueOf(49.90), "Jacket", "Jacket");
			case "TROUSERS": return new Item(BigDecimal.valueOf(35.50), "Trousers", "Trousers");
			case "SHIRT": return new Item(BigDecimal.valueOf(12.50), "Shirt", "Shirt");
			case "TIE": return new Item(BigDecimal.valueOf(9.50), "Tie", "Tie");
			default: return new Item(BigDecimal.ZERO, "Error: Unknown Item, " + name, "Error: Unknown Item");
		}
	}

}
