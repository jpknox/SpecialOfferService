package com.jpknox;

import com.jpknox.basket.ShoppingBasket;
import com.jpknox.io.property.PropertyUtil;
import com.jpknox.item.factory.ItemFactory;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        ShoppingBasket basket = new ShoppingBasket();

        ItemFactory itemFactory = new ItemFactory();
        basket.addItem(itemFactory.create("Jacket"));
        basket.addItem(itemFactory.create("Trousers"));
        basket.addItem(itemFactory.create("Trousers"));
        basket.addItem(itemFactory.create("Shirt"));
        basket.addItem(itemFactory.create("Shirt"));
        basket.addItem(itemFactory.create("Tie"));
        System.out.println(basket.getReceipt());
    }

}