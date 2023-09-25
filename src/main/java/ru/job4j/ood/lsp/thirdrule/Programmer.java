package ru.job4j.ood.lsp.thirdrule;

public class Programmer extends Worker {
     private double salary;
	 private double kpi;

     Programmer(double salary, double kpi) {
        super(salary, kpi);
     }

     @Override
     public void checkPrem() {
          salary *= 1.5;
        }

     @Override
     public double paySalary() {
        return salary;
     }
}
