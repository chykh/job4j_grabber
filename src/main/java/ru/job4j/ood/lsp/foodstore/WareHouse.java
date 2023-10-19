package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class WareHouse extends AbstractStore {
    private List<Food> warehouse = new ArrayList<>();

    public boolean select(Food food) {
        boolean flag = food.getFreshness() > 0.75;
        if  (flag) {
            warehouse.add(food);
            System.out.println(food + " - WareHouse");
        }
        return flag;
    }

    public List<Food> getList() {
        return warehouse;
    }

    public String getStoreName() {
        return ("WareHouse");
    }

}
