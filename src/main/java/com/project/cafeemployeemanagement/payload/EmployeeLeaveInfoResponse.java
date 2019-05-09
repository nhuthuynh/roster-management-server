package com.project.cafeemployeemanagement.payload;

public class EmployeeLeaveInfoResponse {

    private int pendingLeave;

    private int leaveBalance;

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
}
