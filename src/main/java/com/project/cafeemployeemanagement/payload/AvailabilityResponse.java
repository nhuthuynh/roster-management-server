package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;

import java.util.Date;

public class AvailabilityResponse {

    private Long id;

    private String day;

    private int startHour;

    private int startMinute;

    private int endHour;

    private int endMinute;

    private boolean isAvailable;

    @JsonDeserialize(using = CustomDateDeserialize.class)
    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private Date effectiveDate;

    private int version;

    public AvailabilityResponse(Long id, String day, int startHour, int startMinute, int endHour, int endMinute, boolean isAvailable, Date effectiveDate) {
        this.id = id;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.isAvailable = isAvailable;
        this.effectiveDate = effectiveDate;
    }

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
