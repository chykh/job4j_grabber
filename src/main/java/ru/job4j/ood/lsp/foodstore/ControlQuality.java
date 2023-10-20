package ru.job4j.ood.lsp.foodstore;

import java.util.List;

public class ControlQuality {
    private List<Store> stores;
    private List<Food> foods;

    ControlQuality() {
        this.stores = Init.getStores();
        this.foods = Init.getFoods();
    }

    public void sort() throws Exception {
        if (stores.size() < 1) {
            throw new Exception("введен некорректный перечень хранилищ");
        }
        if (foods.size() < 1) {
            throw new Exception("введен некорректный перечень продуктов");
        }

        for (Food food : foods) {
            for (Store store : stores) {
                if (store.select(food)) {
                    break;
                }
            }
        }

    }
}
