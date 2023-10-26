package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParking implements Parking {
    private int freePassPlaces;
    private int freeTruckPlaces;
    private List<Car> passCars = new ArrayList<>();
    private List<Car> trucks = new ArrayList<>();

    public AbstractParking(int freePassPlaces, int freeTruckPlaces) {
        this.freePassPlaces = freePassPlaces;
        this.freeTruckPlaces = freeTruckPlaces;
    }

    public List<Car> getPassCars() {
        return passCars;
    }

    public List<Car> getTrucks() {
        return trucks;
    }

    public int getFreePassPlaces() {
        return freePassPlaces;
    }

    public void setFreePassPlaces(int freePassPlaces) {
        this.freePassPlaces = freePassPlaces;
    }

    public int getFreeTruckPlaces() {
        return freeTruckPlaces;
    }

    public void setFreeTruckPlaces(int freeTruckPlaces) {
        this.freeTruckPlaces = freeTruckPlaces;
    }

    public abstract void addPassCar(Car car);

    public abstract void addTruck(Car car);

    public abstract void specialAdd(Car car);

    public abstract void removePassCar(Car car);

    public abstract void removeTruck(Car car);

    public abstract void specialRemove(Car car);

}
