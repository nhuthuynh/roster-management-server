package com.project.cafeemployeemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.util.utils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roster")
public class Roster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private LocalDate fromDate;

    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private LocalDate toDate;

    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private LocalDate createdDate;

    @OneToMany(mappedBy = "roster",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Shift> shifts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    public void addShift(Shift shift) {
        shifts.add(shift);
        shift.setRoster(this);
    }

    public void removeShift(Shift shift) {
        shifts.remove(shift);
        shift.setRoster(null);
    }

    private boolean isPublished;

    @Version
    private int version;

    public Roster() {}

    public Roster(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = utils.getToday();
        this.isPublished = false;
    }

    public Roster(LocalDate fromDate, LocalDate toDate, LocalDate createdDate, List<Shift> shifts) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = createdDate;
        this.shifts = shifts;
        this.isPublished = false;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Shift> getShiftList() {
        return shifts;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shifts = shiftList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roster )) return false;
        return id != null && id.equals(((Roster) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
