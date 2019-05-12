package com.project.cafeemployeemanagement.payload;

import java.util.ArrayList;
import java.util.List;

public class EmployeeLeaveInfoResponse {

    private int pendingLeave;

    private int leaveBalance;

    private List<EmployeeLeaveRequest> leaveRequests = new ArrayList<>();

    public int getPendingLeave() {
        return pendingLeave;
    }

    public void setPendingLeave(int pendingLeave) {
        this.pendingLeave = pendingLeave;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public List<EmployeeLeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void addLeaveRequest(EmployeeLeaveRequest employeeLeaveRequest) {
        this.leaveRequests.add(employeeLeaveRequest);
    }

    public void setLeaveRequests(List<EmployeeLeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }
}
