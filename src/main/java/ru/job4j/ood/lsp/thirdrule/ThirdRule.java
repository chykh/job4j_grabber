package ru.job4j.ood.lsp.thirdrule;

public class ThirdRule {
    public static void main(String[] args) {
        Worker lucky = new Programmer(100, 0.5);
        lucky.paySalary();
    }
}
