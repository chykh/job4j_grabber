package ru.job4j.ood.srp.report;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportProgrammers implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportProgrammers(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate() {
        return null;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        File file = new File("src\\main\\resources\\reportProgrammers.csv");
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

}
