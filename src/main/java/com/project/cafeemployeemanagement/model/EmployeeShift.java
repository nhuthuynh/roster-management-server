package com.project.cafeemployeemanagement.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.util.CustomTimeDeserialize;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "employee_shift")
public class    EmployeeShift {
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
    @JsonDeserialize(using= CustomTimeDeserialize.class)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using= CustomTimeDeserialize.class)
    private Date endTime;

    private String note;

    public EmployeeShift() {}

    public EmployeeShift(Date startTime, Date endTime, String note) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    public EmployeeShift(Employee employee, Shift shift, Date startTime, Date endTime, String note) {
        this.employee = employee;
        this.shift = shift;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
