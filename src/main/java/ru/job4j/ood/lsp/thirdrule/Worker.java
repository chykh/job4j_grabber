package ru.job4j.ood.lsp.thirdrule;

public class Worker {
    private double salary;
    private double kpi;

    Worker(double salary, double kpi) {
        this.salary = salary;
        this.kpi = kpi;
    }

    public void checkPrem() {
        if (kpi > 1) {
           salary = salary * 1.5;
        }
    }

    public double paySalary() {
        checkPrem();
        return salary;
    }

}
