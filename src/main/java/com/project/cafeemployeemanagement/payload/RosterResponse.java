package com.project.cafeemployeemanagement.payload;

import java.util.ArrayList;
import java.util.List;

public class RosterResponse {
    private Long id;

    private String fromDate;

    private String toDate;

    private List<ShiftResponse> shiftList = new ArrayList<>();

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

    public List<ShiftResponse> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftResponse> shiftList) {
        this.shiftList = shiftList;
    }
}
