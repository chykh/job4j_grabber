package ru.job4j.ood.lsp.parking;

import java.time.LocalDateTime;

public class PassengerCar extends AbstractCar {

    public PassengerCar(String number, LocalDateTime beginTime) {
        super(number, 1, beginTime);
    }

    @Override
    public void select(Parking parking) throws Exception {
        if (parking.getFreePassPlaces() > 0) {
            parking.addPassCar(this);
        } else {
            throw new Exception("легковую машину запарковать нельзя");
        }
    }

}
