package com.project.cafeemployeemanagement.model;

import com.project.cafeemployeemanagement.util.utils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "employee_shift")
public class EmployeeShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    private float workedHours;

    private String note;

    public EmployeeShift() {}

    public EmployeeShift(Date start, Date end, String note) {
        this.start = start;
        this.end = end;
        this.note = note;
    }

    public EmployeeShift(Employee employee, Shift shift, Date start, Date end, String note) {
        this.employee = employee;
        this.shift = shift;
        this.start = start;
        this.end = end;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getWorkedHours() {
        return utils.getNumberOfDifferentHoursBetweenTwoWorkingHours(this.start, this.end);
    }

    public void setWorkedHours(float workedHours) {
        this.workedHours = workedHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeShift employeeShift = (EmployeeShift) o;
        return Objects.equals(employee.getId(), employeeShift.employee.getId()) && Objects.equals(shift.getId(), employeeShift.shift.getId());
    }

    public void addEmployeeShift(Shift shift, Employee employee) {
        setEmployee(employee);
        setShift(shift);
    }

    public void removeEmployeeShift() {
        setEmployee(null);
        setShift(null);
    }
}
