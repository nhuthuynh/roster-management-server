package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.cafeemployeemanagement.constant.Constants;

public class LatestRosterDatesPayLoad {
    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private String fromDate;

    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private String toDate;

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
}
