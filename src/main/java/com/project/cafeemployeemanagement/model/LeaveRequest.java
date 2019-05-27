package com.project.cafeemployeemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.cafeemployeemanagement.util.utils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="leave_request")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy, timezone = UTC")
    private Date fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy, timezone = UTC")
    private Date toDate;

    private Long numberOfOffDates;

    @Enumerated(EnumType.STRING)
    @Column(length=60)
    private LeaveStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    private Long updatedManagerId;

    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus leaveStatus) { this.status = leaveStatus; }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getUpdatedManagerId() {
        return updatedManagerId;
    }

    public void setUpdatedManagerId(Long updatedManagerId) {
        this.updatedManagerId = updatedManagerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public long getNumberOfOffDates() {
        return utils.getNumberOfDifferentDatesBetweenTwoLeaveDates(this.getFromDate(), this.getToDate());
    }
}
