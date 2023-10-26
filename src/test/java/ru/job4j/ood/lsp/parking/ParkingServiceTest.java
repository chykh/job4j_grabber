package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ParkingServiceTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Car car1 = new PassengerCar("L1-01", LocalDateTime.parse("1986-04-08 12:30", formatter));
    Car car2 = new PassengerCar("L1-02", LocalDateTime.parse("1986-04-08 12:30", formatter));
    Car car3 = new PassengerCar("L1-03", LocalDateTime.parse("1986-04-08 12:30", formatter));
    Car car4 = new PassengerCar("L1-04", LocalDateTime.parse("1986-04-08 12:30", formatter));
    Car car5 = new Truck("G1-01", 2, LocalDateTime.parse("1986-04-08 12:30", formatter));
    Car car6 = new Truck("G1-02", 4, LocalDateTime.parse("1986-04-08 12:30", formatter));
    Car car7 = new Truck("G1-03", 2, LocalDateTime.parse("1986-04-08 12:30", formatter));
    Car car8 = new Truck("G1-04", 3, LocalDateTime.parse("1986-04-08 12:30", formatter));

    @Test
    public void whenStandartPartParking() throws Exception {
        Parking parking1 = new Parking1(4, 4);
        ParkingService ps = new ParkingService(parking1);
        car1.select(parking1);
        car2.select(parking1);
        car5.select(parking1);
        car6.select(parking1);
        car7.select(parking1);
        assertThat(ps.getPassPlaces()).isEqualTo("На легковой парковке: занято 2 мест, свободно 2 мест");
        assertThat(ps.getTruckPlaces()).isEqualTo("На грузовой парковке: занято 3 мест, свободно 1 мест");
    }

    @Test
    public void whenStandartFullParking() throws Exception {
        Parking parking1 = new Parking1(4, 4);
        ParkingService ps = new ParkingService(parking1);
        car1.select(parking1);
        car2.select(parking1);
        car3.select(parking1);
        car4.select(parking1);
        car5.select(parking1);
        car6.select(parking1);
        car7.select(parking1);
        car8.select(parking1);
        assertThat(ps.getPassPlaces()).isEqualTo("На легковой парковке: занято 4 мест, свободно 0 мест");
        assertThat(ps.getTruckPlaces()).isEqualTo("На грузовой парковке: занято 4 мест, свободно 0 мест");
    }

    @Test
    public void whenSpecialParking() throws Exception {
        Parking parking1 = new Parking1(10, 2);
        ParkingService ps = new ParkingService(parking1);
        car1.select(parking1);
        car2.select(parking1);
        car3.select(parking1);
        car4.select(parking1);
        car5.select(parking1);
        car6.select(parking1);
        car7.select(parking1);
        car8.select(parking1);
        assertThat(ps.getPassPlaces()).isEqualTo("На легковой парковке: занято 6 мест, свободно 1 мест");
        assertThat(ps.getTruckPlaces()).isEqualTo("На грузовой парковке: занято 2 мест, свободно 0 мест");
    }

    @Test
    public void whenRefillParking() throws Exception {
        Parking parking1 = new Parking1(4, 2);
        ParkingService ps = new ParkingService(parking1);
        car1.select(parking1);
        car2.select(parking1);
        car3.select(parking1);
        car4.select(parking1);
        car5.select(parking1);
        car6.select(parking1);
        assertThat(ps.getPassPlaces()).isEqualTo("На легковой парковке: занято 4 мест, свободно 0 мест");
        assertThat(ps.getTruckPlaces()).isEqualTo("На грузовой парковке: занято 2 мест, свободно 0 мест");
        Throwable thrown = catchThrowable(() -> car7.select(parking1));
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void whenZLeaveParking() throws Exception {
        Parking parking1 = new Parking1(10, 2);
        ParkingService ps = new ParkingService(parking1);
        car1.select(parking1);
        car2.select(parking1);
        car3.select(parking1);
        car4.select(parking1);
        car5.select(parking1);
        car6.select(parking1);
        car7.select(parking1);
        car8.select(parking1);
        assertThat(ps.getPassPlaces()).isEqualTo("На легковой парковке: занято 6 мест, свободно 1 мест");
        assertThat(ps.getTruckPlaces()).isEqualTo("На грузовой парковке: занято 2 мест, свободно 0 мест");
        parking1.specialRemove(car7);
        parking1.specialRemove(car8);
        assertThat(ps.getPassPlaces()).isEqualTo("На легковой парковке: занято 4 мест, свободно 6 мест");
        assertThat(ps.getTruckPlaces()).isEqualTo("На грузовой парковке: занято 2 мест, свободно 0 мест");
        parking1.removeTruck(car6);
        parking1.removePassCar(car2);
        assertThat(ps.getPassPlaces()).isEqualTo("На легковой парковке: занято 3 мест, свободно 7 мест");
        assertThat(ps.getTruckPlaces()).isEqualTo("На грузовой парковке: занято 1 мест, свободно 1 мест");
    }

}