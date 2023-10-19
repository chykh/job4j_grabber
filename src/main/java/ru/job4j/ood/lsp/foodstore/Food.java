package ru.job4j.ood.lsp.foodstore;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price;
    private double discount;
    private double freshness;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price, double discount)
            throws Exception {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
        freshness();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFreshness() {
        return freshness;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return ("name " + name + "/ createDate " + createDate + "/ expiryDate " + expiryDate
                + "/ price " + price + "/ discount " + discount + "/ freshness " + freshness);
    }

    public void freshness() throws Exception {

        long a = ChronoUnit.DAYS.between(createDate, LocalDate.parse("2023-10-19"));
        long b = ChronoUnit.DAYS.between(createDate, expiryDate);
        if (a < 0 || b < 0) {
            throw new Exception("введены некорректные даты");
        } else if (a == 0) {
            freshness = 1.0;
        } else {
            freshness = ((double) (b - a) / b);
        }
    }

}
