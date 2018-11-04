package com.jpknox.item.factory;

import com.jpknox.item.Item;
import com.jpknox.item.catalogue.Catalogue;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by joaok on 03/11/2018.
 */
public class ItemFactory {

	private Catalogue catalogue = new Catalogue();

	public Item create(String name) {
		Item item = catalogue.get(name);
		if (item == null) {
			return new Item("Error: Unknown Item, " + name, BigDecimal.ZERO);
		} else {
			return item.cloneOf();
		}
	}

}
