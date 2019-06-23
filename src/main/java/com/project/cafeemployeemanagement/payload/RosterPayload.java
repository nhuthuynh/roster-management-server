package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.cafeemployeemanagement.constant.Constants;

public class RosterPayload extends LatestRosterDatesPayLoad {
    private Long id;

    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private String createdDate;

    private Long shopOwnerId;

    public RosterPayload() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Long getShopOwnerId() {
        return shopOwnerId;
    }

    public void setShopOwnerId(Long shopOwnerId) {
        this.shopOwnerId = shopOwnerId;
    }
}
