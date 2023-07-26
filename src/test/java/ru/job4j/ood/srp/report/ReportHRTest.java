package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportHRTest {

    @Test
    public void whenFourHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Filip", now, now, 80);
        Employee worker3 = new Employee("Katia", now, now, 150);
        Employee worker4 = new Employee("Tomas", now, now, 90);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        Report report = new ReportHR(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator());

        expect.append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());

        expect.append(worker4.getName()).append(" ")
                .append(worker4.getSalary())
                .append(System.lineSeparator());

        expect.append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator());

        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenFourHRSameValues() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 80);
        Employee worker2 = new Employee("Filip", now, now, 80);
        Employee worker3 = new Employee("Katia", now, now, 150);
        Employee worker4 = new Employee("Tomas", now, now, 90);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        Report report = new ReportHR(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator());

        expect.append(worker4.getName()).append(" ")
                .append(worker4.getSalary())
                .append(System.lineSeparator());

        expect.append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator());

        expect.append(worker1.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator());

        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}