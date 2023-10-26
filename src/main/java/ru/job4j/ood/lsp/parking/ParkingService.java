package ru.job4j.ood.lsp.parking;

public class ParkingService {
    private Parking parking;

    public ParkingService(Parking parking) {
        this.parking = parking;
    }

    public String getPassPlaces() {
        return ("На легковой парковке: занято " + parking.getPassCars().size() + " мест, свободно "
                + parking.getFreePassPlaces() + " мест");
    }

    public String getTruckPlaces() {
        return ("На грузовой парковке: занято " + parking.getTrucks().size() + " мест, свободно "
                + parking.getFreeTruckPlaces() + " мест");
    }

}


