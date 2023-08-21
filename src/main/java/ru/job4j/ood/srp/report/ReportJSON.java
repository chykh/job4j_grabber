package ru.job4j.ood.srp.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    String xml = "";
    List<Employee> employers;
    StringBuilder text = new StringBuilder();
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    public ReportJSON(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    public ReportJSON(Store store, DateTimeParser<Calendar> dateTimeParser, Predicate<Employee> filter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        employers = store.findBy(filter);
    }

    @Override
    public String generate() {
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(employers);
            text.append(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        for (Employee employee : store.findBy(filter)) {
            System.out.println(employee.getFired());
            try {
                String json = objectMapper.writerWithDefaultPrettyPrinter().
                        writeValueAsString(employee);
                text.append(json).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker);
        Report engine = new ReportJSON(store, parser, condition -> true);
        System.out.println(engine.generate());
    }
}
