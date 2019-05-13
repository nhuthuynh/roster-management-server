package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.payload.*;
import com.project.cafeemployeemanagement.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/leaveRequest")
public class LeaveRequestController {
    @Autowired
    LeaveRequestService leaveRequestService;

    @PostMapping("/submission")
    public ResponseEntity<?> submitLeaveRequest(@Valid @RequestBody SubmitLeaveRequest submitLeaveRequest) {
        leaveRequestService.submitLeaveRequest(submitLeaveRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Submit leave request successfully!"));
    }

    @PostMapping("/approval")
    public ResponseEntity<?> approveLeaveRequest(@Valid @RequestBody UpdateLeaveRequest leaveRequest) {
        leaveRequestService.approveLeaveRequest(leaveRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Approve leave request successfully!"));
    }

    @PostMapping("/denial")
    public ResponseEntity<?> denyLeaveRequest(@Valid @RequestBody UpdateLeaveRequest leaveRequest) {
        leaveRequestService.denyLeaveRequest(leaveRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Deny leave request successfully!"));
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<?> loadLeaveRequestsOfEmployee(@PathVariable final long employeeId) {
        EmployeeLeaveInfoResponse employeeLeaveInfoResponse =leaveRequestService.loadLeaveRequestsOfEmployee(employeeId);
        return ResponseEntity.ok(employeeLeaveInfoResponse);
    }

    @GetMapping("/shopOwner/{shopOwnerId}/employees")
    public ResponseEntity<?> loadEmployeeLeaveRequest(@PathVariable final long shopOwnerId) {
        List<LeaveRequestsResponse> leaveRequestsResponseList = leaveRequestService.loadEmployeesLeaveRequests((shopOwnerId));
        return ResponseEntity.ok(leaveRequestsResponseList);
    }

    @GetMapping("/shopOwner/{shopOwnerId}/manager/employees")
    public ResponseEntity<?> loadEmployeesLeaveRequestsForManager(@PathVariable final long shopOwnerId) {
        List<LeaveRequestsResponse> leaveRequestsResponseList = leaveRequestService.loadEmployeesLeaveRequestsForManager((shopOwnerId));
        return ResponseEntity.ok(leaveRequestsResponseList);
    }
}
