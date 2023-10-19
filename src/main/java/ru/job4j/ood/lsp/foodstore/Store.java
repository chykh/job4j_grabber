package ru.job4j.ood.lsp.foodstore;
import java.util.List;

public interface Store {

    public boolean select(Food food);

    public List<Food> getList();

    public void init();

    public void remove(Food food);

    public void showAll();

    public void show(Food food);

    public Food findByName(String request, Store store) throws Exception;

    public String getStoreName();

   }
