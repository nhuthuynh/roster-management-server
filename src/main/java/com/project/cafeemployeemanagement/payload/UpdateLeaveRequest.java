package com.project.cafeemployeemanagement.payload;

import javax.validation.constraints.NotBlank;

public class UpdateLeaveRequest {
    @NotBlank
    private Long managerId;

    @NotBlank
    private Long leaveRequestId;

    private String note;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getLeaveRequestId() {
        return leaveRequestId;
    }

    public void setLeaveRequestId(Long leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
