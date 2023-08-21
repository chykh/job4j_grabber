package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ReportXMLTest {

    @Test
    void whenEverythingIsOk() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vovan", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportXML(store, parser, condition -> true);
        String time = parser.parse(now);
        String ls = "\n";
        String expected =   "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + ls +
                            "<employee>" + ls +
                            "    <name>Ivan</name>" + ls +
                            "    <hired>" + time + "</hired>" + ls +
                            "    <fired>" + time + "</fired>" + ls +
                            "    <salary>100.0</salary>" + ls +
                            "</employee>" + ls +
                            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + ls +
                            "<employee>" + ls +
                            "    <name>Vovan</name>" + ls +
                            "    <hired>" + time + "</hired>" + ls +
                            "    <fired>" + time + "</fired>" + ls +
                            "    <salary>200.0</salary>" + ls +
                            "</employee>" + ls;
        assertEquals(expected, engine.generate());
    }
}