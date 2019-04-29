package com.project.cafeemployeemanagement.payload;

import java.util.ArrayList;
import java.util.List;

public class AvailabilityRequest {
    private List<AvailabilityResponse> availabilityList = new ArrayList<>();
    private Long employeeId;

    public List<AvailabilityResponse> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<AvailabilityResponse> availabilityList) {
        this.availabilityList = availabilityList;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
