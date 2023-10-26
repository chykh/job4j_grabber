package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    List<Car> getPassCars();

    List<Car> getTrucks();

    int getFreePassPlaces();

    void setFreePassPlaces(int freePassPlaces);

    int getFreeTruckPlaces();

    void setFreeTruckPlaces(int freeTruckPlaces);

    void addPassCar(Car car);

    void addTruck(Car car);

    void specialAdd(Car car);

    void removePassCar(Car car);

    void removeTruck(Car car);

    void specialRemove(Car car);
}
