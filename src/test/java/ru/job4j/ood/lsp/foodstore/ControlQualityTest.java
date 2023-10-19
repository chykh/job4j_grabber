package ru.job4j.ood.lsp.foodstore;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {

    ControlQualityTest() throws Exception {
    }

    Store wareHouse = new WareHouse();
    Store shop = new Shop();
    Store trash = new Trash();
    List<Store> stores = Arrays.asList(wareHouse, shop, trash);

    Food apple = new Food("apple", LocalDate.parse("2023-10-10"),
            LocalDate.parse("2023-12-30"), 70, 0);
    Food milk = new Food("milk", LocalDate.parse("2023-10-15"),
            LocalDate.parse("2023-10-17"), 45, 0);
    Food sugar = new Food("sugar", LocalDate.parse("2023-08-05"),
            LocalDate.parse("2023-12-30"), 60, 0);
    Food tea = new Food("tea", LocalDate.parse("2023-10-10"),
            LocalDate.parse("2023-10-20"), 100, 0);
    Food orange = new Food("orange", LocalDate.parse("2023-10-10"),
            LocalDate.parse("2023-10-30"), 120, 0);
    Food cola = new Food("cola", LocalDate.parse("2023-07-10"),
            LocalDate.parse("2023-11-30"), 140, 0);
    Food juice = new Food("juice", LocalDate.parse("2023-10-10"),
            LocalDate.parse("2023-12-21"), 70, 0);
    Food bread = new Food("bread", LocalDate.parse("2023-09-16"),
            LocalDate.parse("2023-10-02"), 55, 0);
    Food chocolate = new Food("chocolate", LocalDate.parse("2023-02-14"),
            LocalDate.parse("2024-09-16"), 120, 0);
    Food yogurt = new Food("yogurt", LocalDate.parse("2023-10-12"),
            LocalDate.parse("2023-12-15"), 40, 0);
    Food spaghetti = new Food("spaghetti", LocalDate.parse("2023-06-12"),
            LocalDate.parse("2023-11-20"), 75, 0);
    Food vodka = new Food("vodka", LocalDate.parse("1991-01-01"),
            LocalDate.parse("2023-10-30"), 1000, 0);
    List<Food> foods = Arrays.asList(apple, milk, sugar, tea, orange, cola, juice, bread,
            chocolate, yogurt, spaghetti, vodka);

    Init init = new Init(stores, foods);
    ControlQuality cq = new ControlQuality();

    @Test
    public void controlQualitySortingTest() throws Exception {
        System.out.println("ПОСЛЕ СОРТИРОВКИ");
        cq.sort();
        assertEquals(apple, wareHouse.findByName("apple", wareHouse));
        assertEquals(milk, trash.findByName("milk", trash));
        assertEquals(sugar, shop.findByName("sugar", shop));
        assertEquals(tea, shop.findByName("tea", shop));
        assertEquals(orange, shop.findByName("orange", shop));
        assertEquals(cola, shop.findByName("cola", shop));
        assertEquals(juice, wareHouse.findByName("juice", wareHouse));
        assertEquals(bread, trash.findByName("bread", trash));
        assertEquals(chocolate, shop.findByName("chocolate", shop));
        assertEquals(yogurt, wareHouse.findByName("yogurt", wareHouse));
        assertEquals(spaghetti, shop.findByName("spaghetti", shop));
        assertEquals(vodka, shop.findByName("vodka", shop));
    }
}