package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShiftRequest {

    @JsonDeserialize(using = CustomDateDeserialize.class)
    private Date date;

    private List<EmployeeShiftRequest> employeeShifts = new ArrayList<>();

    private String note;

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

    public List<EmployeeShiftRequest> getEmployeeShifts() {
        return employeeShifts;
    }

    public void setEmployeeShifts(List<EmployeeShiftRequest> employeeShifts) {
        this.employeeShifts = employeeShifts;
    }
}
