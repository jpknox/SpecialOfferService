package com.jpknox.item.catalogue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpknox.io.property.PropertyUtil;
import com.jpknox.item.Item;
import com.jpknox.item.Purchasable;

import java.io.IOException;
import java.util.*;

/**
 * Created by joaok on 04/11/2018.
 */
public class Catalogue {

	private Map<String, Item> catalogue = new HashMap();

	public Catalogue() {
		PropertyUtil propertyUtil = new PropertyUtil();
		Properties props = propertyUtil.getPropertyFile("product-catalogue.properties");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Item[] items = objectMapper.readValue(props.getProperty("catalogue"), Item[].class);
			Arrays.asList(items).stream().forEach( i -> {
					catalogue.put(i.getName().toUpperCase(), i);
			});
		} catch (IOException e) {
			System.out.println("Error parsing JSON product catalogue into POJOs.");
			e.printStackTrace();
		}
	}

	public Item get(String name) {
		return catalogue.get(name.toUpperCase());
	}
}
