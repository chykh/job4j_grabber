package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {
    private List<Food> trash = new ArrayList<>();

    public boolean select(Food food) {
        boolean flag = food.getFreshness() < 0;
        if (flag) {
            trash.add(food);
            System.out.println(food + " - Trash");
        }
        return flag;
    }

    public List<Food> getList() {
        return trash;
    }

    public String getStoreName() {
        return ("Trash");
    }
}
