package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.*;
import java.util.function.Predicate;

public class ReportHR implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportHR(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());

        List<Employee> employees = store.findBy(filter);
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee left, Employee right) {
                return left.getSalary() < right.getSalary() ? 1 : -1;
            }
        });

        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
