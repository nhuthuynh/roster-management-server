package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.util.CustomTimeDeserialize;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class EmployeeShiftRequest {

    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using= CustomTimeDeserialize.class)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using= CustomTimeDeserialize.class)
    private Date endTime;

    private Long employeeId;

    private String note;

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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
