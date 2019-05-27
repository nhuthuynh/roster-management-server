package com.project.cafeemployeemanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;

    private int startHour;

    private int startMinute;

    private int endHour;

    private int endMinute;

    private boolean isAvailable;

    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Availability() {}

    public Availability(Long id, String day, int startHour, int startMinute, int endHour, int endMinute, boolean isAvailable) {
        this.id = id;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.isAvailable = isAvailable;
    }

    public Availability(String day, int startHour, int startMinute, int endHour, int endMinute, boolean isAvailable) {
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.isAvailable = isAvailable;
    }

    public Availability(Long id, String day, int startHour, int startMinute, int endHour, int endMinute, boolean isAvailable, Employee employee) {
        this.id = id;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.employee = employee;
        this.isAvailable = isAvailable;
    }

    public Availability(Long id, String day, int startHour, int startMinute, int endHour, int endMinute, boolean isAvailable, Employee employee, Date effectiveDate) {
        this.id = id;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.employee = employee;
        this.isAvailable = isAvailable;
        this.effectiveDate = effectiveDate;
    }

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
