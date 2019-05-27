package com.project.cafeemployeemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "roster")
public class Roster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy, timezone = UTC")
    private Date fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy, timezone = UTC")
    private Date toDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy, timezone = UTC")
    private Date createdDate;

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

    public Roster(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = new Date();
        this.isPublished = false;
    }

    public Roster(Date fromDate, Date toDate, Date createdDate, List<Shift> shifts) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = createdDate;
        this.shifts = shifts;
        this.isPublished = false;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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
