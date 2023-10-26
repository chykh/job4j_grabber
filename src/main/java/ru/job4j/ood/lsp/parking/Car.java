package ru.job4j.ood.lsp.parking;

import java.time.LocalDateTime;

public interface Car {

    void select(Parking parking) throws Exception;

    String toString();

    int getSize();

    String getNumber();

    LocalDateTime getBeginTime();

}
