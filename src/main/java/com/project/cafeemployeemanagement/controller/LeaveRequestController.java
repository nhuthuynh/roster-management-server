package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.payload.ApiResponse;
import com.project.cafeemployeemanagement.payload.EmployeeLeaveInfoResponse;
import com.project.cafeemployeemanagement.payload.SubmitLeaveRequest;
import com.project.cafeemployeemanagement.payload.UpdateLeaveRequest;
import com.project.cafeemployeemanagement.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/leaveRequest")
public class LeaveRequestController {
    @Autowired
    LeaveRequestService leaveRequestService;

    @PostMapping("/submit")
    public ApiResponse submitLeaveRequest(@Valid @RequestBody SubmitLeaveRequest submitLeaveRequest) {
        leaveRequestService.submitLeaveRequest(submitLeaveRequest);
        return new ApiResponse(true, "Submit leave request successfully!");
    }

    @PostMapping("/approve")
    public ApiResponse approveLeaveRequest(@Valid @RequestBody UpdateLeaveRequest leaveRequest) {
        leaveRequestService.approveLeaveRequest(leaveRequest);
        return new ApiResponse(true, "Approve leave request successfully!");
    }

    @PostMapping("/deny")
    public ApiResponse denyLeaveRequest(@Valid @RequestBody UpdateLeaveRequest leaveRequest) {
        leaveRequestService.denyLeaveRequest(leaveRequest);
        return new ApiResponse(true, "Deny leave request successfully!");
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> loadLeaveRequestsOfEmployee(@PathVariable final long employeeId) {
        EmployeeLeaveInfoResponse employeeLeaveInfoResponse =leaveRequestService.loadLeaveRequestsOfEmployee(employeeId);

        return ResponseEntity.status(HttpStatus.OK).body(employeeLeaveInfoResponse);
    }
}
