package ru.job4j.ood.srp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.job4j.ood.srp.report.ReportXML;
import java.util.Calendar;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employee")
public class Employee {
    @XmlElement
    private String name;
    @JsonFormat(pattern = "dd:MM:yyyy HH:mm", timezone = "Europe/Moscow")
    @XmlElement
    @XmlJavaTypeAdapter(ReportXML.LDTAdapter.class)
    private Calendar hired;
    @JsonFormat(pattern = "dd:MM:yyyy HH:mm", timezone = "Europe/Moscow")
    @XmlElement
    @XmlJavaTypeAdapter(ReportXML.LDTAdapter.class)
    private Calendar fired;
    @XmlElement
    private double salary;

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
