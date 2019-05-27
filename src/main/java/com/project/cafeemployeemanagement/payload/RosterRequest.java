package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RosterRequest {

    private Long id;

    @JsonDeserialize(using = CustomDateDeserialize.class)
    private Date fromDate;

    @JsonDeserialize(using = CustomDateDeserialize.class)
    private Date toDate;

    @JsonDeserialize(using = CustomDateDeserialize.class)
    private Date createdDate;

    private Long shopOwnerId;

    private List<ShiftRequest> shiftList = new ArrayList<>();

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

    public Long getShopOwnerId() {
        return shopOwnerId;
    }

    public void setShopOwnerId(Long shopOwnerId) {
        this.shopOwnerId = shopOwnerId;
    }
}
