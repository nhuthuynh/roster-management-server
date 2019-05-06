package com.project.cafeemployeemanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roster_id")
    private Roster roster;

    @OneToMany(mappedBy = "shift",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<EmployeeShift> employeeShifts = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String note;

    public Shift() {}

    public Shift(Roster roster, Date date, String note) {
        this.roster = roster;
        this.date = date;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<EmployeeShift> getEmployeeShifts() {
        return employeeShifts;
    }

    public void setEmployeeShifts(List<EmployeeShift> employeeShifts) {
        this.employeeShifts = employeeShifts;
    }

    public void addEmployeeShift(EmployeeShift employeeShift, Employee employee) {
        employeeShift.setEmployee(employee);
        employeeShift.setShift(this);
        employeeShifts.add(employeeShift);
    }

    public void removeEmployeeShift(EmployeeShift employeeShift) {
        employeeShifts.remove(employeeShift);
        employeeShift.setShift(null);
        employeeShift.setEmployee(null);
    }
}
