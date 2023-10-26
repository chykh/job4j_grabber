package ru.job4j.ood.lsp.parking;

import java.time.LocalDateTime;

public abstract class AbstractCar implements Car {
    private String number;
    private int size;
    private LocalDateTime beginTime;

    public AbstractCar(String number, int size, LocalDateTime beginTime) {
        this.number = number;
        this.size = size;
        this.beginTime = beginTime;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public int getSize() {
        return this.size;
    }

    public abstract void select(Parking parking) throws Exception;

    @Override
    public String toString() {
        return "number " + number + " / size " + size + " / beginTime " + beginTime;
    }
}
