package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class ReportJSONTest {

    @Test
    void whenEverythingIsOk() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Aleksei", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportJSON(store, parser, condition -> true);
        String time = parser.parse(now);
        String ls = System.lineSeparator();
        String expected = "[ {" + ls +
                "  \"name\" : \"Ivan\"," + ls +
                "  \"hired\" : \"" + time + "\"," + ls +
                "  \"fired\" : \"" + time + "\"," + ls +
                "  \"salary\" : 100.0" + ls +
                "}, {" + ls +
                "  \"name\" : \"Aleksei\"," + ls +
                "  \"hired\" : \"" + time + "\"," + ls +
                "  \"fired\" : \"" + time + "\"," + ls +
                "  \"salary\" : 200.0" + ls +
                "} ]" ;
        assertEquals(expected, engine.generate());
    }
}