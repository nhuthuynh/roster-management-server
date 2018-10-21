package com.project.cafeemployeemanagement.payload;

public class ShiftResponse {
    private Long id;

    private String start;

    private String end;

    private String title;

    private Long shiftId;

    private Long employeeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
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
