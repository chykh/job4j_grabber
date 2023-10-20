package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    private List<Food> shop = new ArrayList<>();
    private final int discount = 20;

    public boolean select(Food food) {
        boolean flag = food.getFreshness() > 0.25 && food.getFreshness() < 0.75;
        if (!flag && food.getFreshness() > 0 && food.getFreshness() < 0.25) {
            flag = true;
            food.setDiscount(discount);
            food.setPrice(food.getPrice() * ((100.0 - discount) / 100));
        }
        if (flag) {
            shop.add(food);
            System.out.println(food + " - Shop");
        }
        return flag;
    }

    public List<Food> getList() {
        return shop;
    }

    public String getStoreName() {
        return ("Shop");
    }
}