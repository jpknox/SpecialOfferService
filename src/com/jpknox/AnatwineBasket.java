package com.jpknox;

import com.jpknox.basket.ShoppingBasket;
import com.jpknox.io.property.PropertyUtil;
import com.jpknox.item.factory.ItemFactory;

import java.util.Arrays;
import java.util.Properties;

public class AnatwineBasket {

    public static void main(String[] args) {
        ShoppingBasket basket = new ShoppingBasket();
        ItemFactory itemFactory = new ItemFactory();
        Arrays.stream(args).forEach( arg -> basket.addItem(itemFactory.create(arg)) );
        System.out.println(basket.getReceipt());
    }

}