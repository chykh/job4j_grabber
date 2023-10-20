package ru.job4j.ood.lsp.foodstore;
import java.util.List;

abstract public class AbstractStore implements Store {
    private List<Store> stores;
    private List<Food> foods;

    public void init() {
        stores = Init.getStores();
        foods = Init.getFoods();
    }

    public void showAll() {
        for (Store store : stores) {
            System.out.println(store.getStoreName());
            for (Food food : store.getList()) {
                System.out.println(food);
            }
        }
    }

    public void remove(Food food) {
        for (Store store : stores) {
            store.getList().removeIf(f -> f.equals(food));
        }
    }

    public void show(Food food) {
        for (Store store : stores) {
            for (Food f : store.getList()) {
                if (f.equals(food)) {
                    System.out.println(f);
                }
            }
        }
    }

    public Food findByName(String request, Store store) throws Exception {
        Food result = null;
        List<Food> foodInStore = store.getList();
        for (int i = 0; i < foodInStore.size(); i++) {
            Food food = foodInStore.get(i);
            if (request.equals(food.getName())) {
                result = food;
                break;
            }
            if (i == foodInStore.size() - 1) {
                throw new Exception("запрос на некорретное хранилище");
            }
        }
        return result;
    }

}
