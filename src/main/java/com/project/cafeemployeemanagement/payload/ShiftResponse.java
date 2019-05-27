package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.util.CustomDateTimeDeserialize;

import java.util.Date;

public class ShiftResponse {
    private Long id;

    @JsonDeserialize(using = CustomDateTimeDeserialize.class)
    private Date start;

    @JsonDeserialize(using = CustomDateTimeDeserialize.class)
    private Date end;

    private String title;

    private Long shiftId;

    private Long employeeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftID) {
        this.shiftId = shiftID;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeID) {
        this.employeeId = employeeID;
    }
}
