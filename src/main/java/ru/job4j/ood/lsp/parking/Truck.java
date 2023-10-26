package ru.job4j.ood.lsp.parking;

import java.time.LocalDateTime;

public class Truck extends AbstractCar {

    public Truck(String number, int size, LocalDateTime beginTime) {
        super(number, size, beginTime);
    }

    public void select(Parking parking) throws Exception {
        if (parking.getFreeTruckPlaces() > 0) {
            parking.addTruck(this);
        } else if (super.getSize() <= parking.getFreePassPlaces()) {
            parking.specialAdd(this);
        } else {
            throw new Exception("грузовую машину запарковать нелья");
        }
    }

}
