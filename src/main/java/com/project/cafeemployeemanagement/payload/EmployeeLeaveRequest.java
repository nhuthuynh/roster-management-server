package com.project.cafeemployeemanagement.payload;

public class EmployeeLeaveRequest {
    public Long id;

    private String fromDate;

    private String toDate;

    private Long numberOfOffDates;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Long getNumberOfOffDates() {
        return numberOfOffDates;
    }

    public void setNumberOfOffDates(Long numberOfOffDates) {
        this.numberOfOffDates = numberOfOffDates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
