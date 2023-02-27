package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HabrCareerDateTimeParser implements  DateTimeParser {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public LocalDateTime parseDate(String date) {
        date = date.split("\\+")[0];
        return LocalDateTime.parse(date, formatter);
    }
}
