/*
        parking.add(car1); запускает ParkingService.check(), заносит в PassengerCar.List<Car>
        parking.add(car2); запускает ParkingService.check(), заносит в PassengerCar.List<Car>
        parking.add(car3); запускает ParkingService.check(), заносит в Truck.List<Car>
        parking.add(car3); запускает ParkingService.check(), заносит в PassengerCar.List<Car>, тк Truck.List<Car> заполнен
 */

package ru.job4j.ood.lsp.parking;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ParkingServiceTest {
    Car car1 = new PassengerCar("a123", 1, LocalDate.parse("2023-09-12"));
    Car car2 = new PassengerCar("a456", 1, LocalDate.parse("2023-09-24"));
    Car car3 = new Truck("a789", 2, LocalDate.parse("2023-11-01"));
    Car car4 = new Truck("b563", 2, LocalDate.parse("2023-08-01"));

    Parking parking = new Parking1(6, 1, new ParkingService());
    parking.add(car1);
    parking.add(car2);
    parking.add(car3);
    parking.add(car3);

    @Test
    void getCarCheck() {
        AssertEquauls(Parking1.getCar("a123"), car1);
    }

        @Test
    void getListCheck() {
        AssertEquauls(Parking1.getList(car1), "PassengerCar");
        AssertEquauls(Parking1.getList(car2), "PassengerCar");
        AssertEquauls(Parking1.getList(car3), "Truck");
        AssertEquauls(Parking1.getList(car4), "PassengerCar");
    }

    @Test
    void removeCheck() {
        Parking1.removeCar("a123");
        AssertEquauls(Parking1.getList(car1), "Машина не найдена");
    }
}