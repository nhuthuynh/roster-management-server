package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RosterResponse {
    private Long id;

    @JsonDeserialize(using = CustomDateDeserialize.class)
    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private Date fromDate;

    @JsonDeserialize(using = CustomDateDeserialize.class)
    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private Date toDate;

    private List<ShiftResponse> shiftList = new ArrayList<>();

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

    public List<ShiftResponse> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftResponse> shiftList) {
        this.shiftList = shiftList;
    }
}
