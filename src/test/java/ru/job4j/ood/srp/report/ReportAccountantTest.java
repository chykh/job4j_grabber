package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.currency.*;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReportAccountantTest {

    @Test
    public void whenFourAccountantsUS() {
        Currency currencyUS = Currency.USD;
        CurrencyConverter cur = new InMemoryCurrencyConverter();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Filip", now, now, 80);
        Employee worker3 = new Employee("Katia", now, now, 140);
        Employee worker4 = new Employee("Tomas", now, now, 60);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        Report report = new ReportAccountant(store, parser, currencyUS);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary/RUB; Salary/USD;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(parser.parse(worker1.getHired())).append(" ")
                .append(parser.parse(worker1.getFired())).append(" ")
                .append(worker1.getSalary()).append(" ")
                .append(cur.convert(Currency.RUB, worker1.getSalary(), currencyUS))
                .append(System.lineSeparator());

        expect.append(worker2.getName()).append(" ")
                .append(parser.parse(worker2.getHired())).append(" ")
                .append(parser.parse(worker2.getFired())).append(" ")
                .append(worker2.getSalary()).append(" ")
                .append(cur.convert(Currency.RUB, worker2.getSalary(), currencyUS))
                .append(System.lineSeparator());

        expect.append(worker3.getName()).append(" ")
                .append(parser.parse(worker3.getHired())).append(" ")
                .append(parser.parse(worker3.getFired())).append(" ")
                .append(worker3.getSalary()).append(" ")
                .append(cur.convert(Currency.RUB, worker3.getSalary(), currencyUS))
                .append(System.lineSeparator());

        expect.append(worker4.getName()).append(" ")
                .append(parser.parse(worker4.getHired())).append(" ")
                .append(parser.parse(worker4.getFired())).append(" ")
                .append(worker4.getSalary()).append(" ")
                .append(cur.convert(Currency.RUB, worker4.getSalary(), currencyUS))
                .append(System.lineSeparator());

        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenThreeAccountantsEU() {
        Currency currencyEU = Currency.EUR;
        CurrencyConverter cur = new InMemoryCurrencyConverter();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Filip", now, now, 80);
        Employee worker3 = new Employee("Katia", now, now, 140);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new ReportAccountant(store, parser, currencyEU);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary/RUB; Salary/EUR;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(parser.parse(worker1.getHired())).append(" ")
                .append(parser.parse(worker1.getFired())).append(" ")
                .append(worker1.getSalary()).append(" ")
                .append(cur.convert(Currency.RUB, worker1.getSalary(), currencyEU))
                .append(System.lineSeparator());

        expect.append(worker2.getName()).append(" ")
                .append(parser.parse(worker2.getHired())).append(" ")
                .append(parser.parse(worker2.getFired())).append(" ")
                .append(worker2.getSalary()).append(" ")
                .append(cur.convert(Currency.RUB, worker2.getSalary(), currencyEU))
                .append(System.lineSeparator());

        expect.append(worker3.getName()).append(" ")
                .append(parser.parse(worker3.getHired())).append(" ")
                .append(parser.parse(worker3.getFired())).append(" ")
                .append(worker3.getSalary()).append(" ")
                .append(cur.convert(Currency.RUB, worker3.getSalary(), currencyEU))
                .append(System.lineSeparator());

        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}