package ru.job4j.ood.lsp.secondrule;

public class Plane {
    private int passengers;
    private double parcels;

    public Plane(int passengers, double parcels) {
        this.passengers = passengers;
        this.parcels = parcels;
    }

    public boolean validate() {
        return (passengers < 100 && parcels < 1500.0);
    }

    public void fly() { }

    public void transportate() throws Exception {
        if (validate()) {
            fly();
        }
    }
}
