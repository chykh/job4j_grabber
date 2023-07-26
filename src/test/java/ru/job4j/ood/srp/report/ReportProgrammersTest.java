package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.assertj.core.api.Assertions.assertThat;

class ReportProgrammersTest {

    @Test
    void whenFilesEqual() {
        File expected = new File("src\\main\\resources\\reportProgrammersExpected.csv");
        MemStore store = new MemStore();
        Calendar calendar = new GregorianCalendar(12, Calendar.DECEMBER, 12, 12, 12, 12);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker1 = new Employee("Ivan", calendar, calendar, 100);
        Employee worker2 = new Employee("Kirill", calendar, calendar, 20);
        Employee worker3 = new Employee("Pavel", calendar, calendar, 100500);
        Employee worker4 = new Employee("Anna", calendar, calendar, 700);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        Report engine = new ReportProgrammers(store, parser);
        engine.generate(employee -> true);
        File actual = new File("src\\main\\resources\\reportProgrammers.csv");
        assertThat(actual).hasSameTextualContentAs(expected);
    }
}