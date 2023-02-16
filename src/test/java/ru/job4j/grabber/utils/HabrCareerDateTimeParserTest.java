package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HabrCareerDateTimeParserTest {

    @Test
    public void whenFirstVariant() {
        String text1 = "2011-12-03T10:15:30+03:00";
        String text2 = "2011-12-03T10:15:30";
        HabrCareerDateTimeParser hcdtp = new HabrCareerDateTimeParser();
        LocalDateTime parsedDate = hcdtp.parseDate(text1);
        assertEquals(text2, parsedDate.format(hcdtp.formatter));
    }

    @Test
    public void whenSecondVariant() {
        String text1 = "2031-02-01T12:11:10+01:00";
        String text2 = "2031-02-01T12:11:10";
        HabrCareerDateTimeParser hcdtp = new HabrCareerDateTimeParser();
        LocalDateTime parsedDate = hcdtp.parseDate(text1);
        assertEquals(text2, parsedDate.format(hcdtp.formatter));
    }
}
