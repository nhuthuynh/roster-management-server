package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;
import com.project.cafeemployeemanagement.util.LocalDateDeserialize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RosterResponse {
    private Long id;

    @JsonDeserialize(using = LocalDateDeserialize.class)
    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private LocalDate fromDate;

    @JsonDeserialize(using = LocalDateDeserialize.class)
    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private LocalDate toDate;

    private List<ShiftResponse> shiftList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public List<ShiftResponse> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftResponse> shiftList) {
        this.shiftList = shiftList;
    }
}
