package ru.job4j.ood.lsp.parking;

public class Parking1 extends AbstractParking {

    public Parking1(int freePassPlaces, int freeTruckPlaces) {
        super(freePassPlaces, freeTruckPlaces);
    }

    public void addPassCar(Car car) {
        getPassCars().add(car);
        super.setFreePassPlaces(super.getFreePassPlaces() - 1);
        System.out.println("легковая машина " + car.getNumber() + " припаркована на легковой парковке");
    }

    public void addTruck(Car car) {
        getTrucks().add(car);
        super.setFreeTruckPlaces(super.getFreeTruckPlaces() - 1);
        System.out.println("грузовая машина " + car.getNumber() + " припаркована на грузовой парковке");
    }

    public void specialAdd(Car car) {
        getPassCars().add(car);
        super.setFreePassPlaces(super.getFreePassPlaces() - car.getSize());
        System.out.println("грузовая машина " + car.getNumber() + " припаркована на ЛЕГКОВОЙ парковке");
    }

    public void removePassCar(Car car) {
        getPassCars().remove(car);
        super.setFreePassPlaces(super.getFreePassPlaces() + 1);
        System.out.println("легковая машина " + car.getNumber() + " покинула легковую парковку");
    }

    public void removeTruck(Car car) {
        getTrucks().remove(car);
        super.setFreeTruckPlaces(super.getFreeTruckPlaces() + 1);
        System.out.println("грузовая машина " + car.getNumber() + " покинула грузовую парковку");
    }

    public void specialRemove(Car car) {
        getPassCars().remove(car);
        super.setFreePassPlaces(super.getFreePassPlaces() + car.getSize());
        System.out.println("грузовая машина " + car.getNumber() + " покинула ЛЕГКОВУЮ парковку");
    }

}
