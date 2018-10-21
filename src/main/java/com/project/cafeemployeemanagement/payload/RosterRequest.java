package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RosterRequest {

    @JsonDeserialize(using= CustomDateDeserialize.class)
    private Date fromDate;

    @JsonDeserialize(using= CustomDateDeserialize.class)
    private Date toDate;

    @JsonDeserialize(using= CustomDateDeserialize.class)
    private Date createdDate;

    private List<ShiftRequest> shiftList = new ArrayList<>();

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

    public List<ShiftRequest> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftRequest> shiftList) {
        this.shiftList = shiftList;
    }

}
