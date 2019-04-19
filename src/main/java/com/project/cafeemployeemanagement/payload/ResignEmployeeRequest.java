package com.project.cafeemployeemanagement.payload;

import java.util.List;

public class ResignEmployeeRequest {
    private List<Long> employeesIdList;

    public List<Long> getEmployeesIdList() {
        return employeesIdList;
    }

    public void setEmployeesIdList(List<Long> employeesIdList) {
        this.employeesIdList = employeesIdList;
    }
}
