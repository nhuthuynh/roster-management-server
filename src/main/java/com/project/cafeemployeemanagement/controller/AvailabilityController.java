package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.payload.ApiResponse;
import com.project.cafeemployeemanagement.payload.AvailabilityRequest;
import com.project.cafeemployeemanagement.payload.AvailabilityResponse;
import com.project.cafeemployeemanagement.service.AvailabilityService;
import com.project.cafeemployeemanagement.util.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> loadAvailabilities(@PathVariable("employeeId") final Long employeeId) {
        List<AvailabilityResponse> availabilityList = availabilityService.findResponsesByEmployeeId(employeeId);
        if (availabilityList.size() == 0) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed to load employee's availability"));
        }
        return ResponseEntity.ok(availabilityList);
    }

    @GetMapping("/toDate/{toDate}/employee/{employeeId}")
    public ResponseEntity<?> loadEmployeeAvailabilities(@PathVariable("employeeId") final Long employeeId, @PathVariable("toDate") @DateTimeFormat(pattern = Constants.DATE_FORMAT) final String rosterToDate) {
        List<AvailabilityResponse> availabilityList = availabilityService.findResponsesByEffectiveDateAfterAndEmployeeId(utils.parseLocalDate(rosterToDate), employeeId);
        if (availabilityList.size() == 0) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed to load employee's availability"));
        }
        return ResponseEntity.ok(availabilityList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAvailabilities(@Valid @RequestBody final AvailabilityRequest availabilityRequest) {

        if (!availabilityService.saveAll(availabilityRequest)) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed to update!"));
        }

        return ResponseEntity.ok().body(new ApiResponse(true, "Update availability successful!"));
    }
}
