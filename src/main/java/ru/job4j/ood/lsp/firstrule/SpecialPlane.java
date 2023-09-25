package ru.job4j.ood.lsp.firstrule;

public class SpecialPlane extends Plane {
    private int passengers;
    private double parcels;

    public SpecialPlane(int passengers, double parcels) {
        super(passengers, parcels);
    }

    @Override
    public boolean validate() {
        double totalWeight = passengers * 100 + parcels;
        return (passengers < 100 && parcels < 1500.0 && totalWeight < 1500.0);
    }

    public void fly() {
    }

    @Override
    public void transportate() {
        if (validate()) {
            fly();
        }
    }
}
