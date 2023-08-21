package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    JAXBContext context = null;
    Marshaller marshaller = null;
    String xml = "";
    List<Employee> employers;

    public ReportXML(Store store, DateTimeParser<Calendar> dateTimeParser, Predicate<Employee> filter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        employers = store.findBy(filter);
        try {
            context = JAXBContext.newInstance(Employee.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ReportXML(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        try {
            context = JAXBContext.newInstance(Employee.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class LDTAdapter extends XmlAdapter<String, Calendar> {
        public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

        @Override
        public Calendar unmarshal(String s) throws Exception {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DATE_FORMAT.parse(s));
            return calendar;
        }

        @Override
        public String marshal(Calendar calendar) throws Exception {
            return  DATE_FORMAT.format(calendar.getTime());
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        for (Employee employee : store.findBy(filter)) {
             try (StringWriter writer = new StringWriter()) {
                 marshaller.marshal(employee, writer);
                 xml = xml.concat(writer.getBuffer().toString());
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
        }
        return xml;
    }

    @Override
    public String generate() {
        for (Employee employee : employers) {
             try (StringWriter writer = new StringWriter()) {
                 marshaller.marshal(employee, writer);
                 xml = xml.concat(writer.getBuffer().toString());
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
        }
        return xml;
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vovan", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportXML(store, parser, condition -> true);
        System.out.println(engine.generate());
    }
}

