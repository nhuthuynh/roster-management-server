package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;
import com.project.cafeemployeemanagement.util.LocalDateDeserialize;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RosterRequest {

    private Long id;

    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private String fromDate;

    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private String toDate;

    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private String createdDate;

    private Long shopOwnerId;

    private List<ShiftRequest> shiftList = new ArrayList<>();

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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
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
