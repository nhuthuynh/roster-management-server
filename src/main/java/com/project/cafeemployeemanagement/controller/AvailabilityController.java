package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.payload.ApiResponse;
import com.project.cafeemployeemanagement.payload.AvailabilityRequest;
import com.project.cafeemployeemanagement.payload.AvailabilityResponse;
import com.project.cafeemployeemanagement.service.AvailabilityService;
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

    @GetMapping("/load")
    public ResponseEntity<?> loadAvailabilities(@RequestParam("employeeId") Long employeeId) {
        List<AvailabilityResponse> availabilityList = availabilityService.findResponsesByEmployeeId(employeeId);
        if (availabilityList.size() == 0) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed to load employee's availability"));
        }
        return ResponseEntity.ok(availabilityList);
    }

    @GetMapping("/load/effectiveDate/{effectiveDate}/employee/{employeeId}")
    public ResponseEntity<?> loadEmployeeAvailabilities(@PathVariable("employeeId") final Long employeeId, @PathVariable("effectiveDate") @DateTimeFormat(pattern = Constants.DATE_FORMAT) final Date effectiveDate) {
        List<AvailabilityResponse> availabilityList = availabilityService.findResponsesByEffectiveDateAfterAndEmployeeId(effectiveDate, employeeId);
        if (availabilityList.size() == 0) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed to load employee's availability"));
        }
        return ResponseEntity.ok(availabilityList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAvailabilities(@Valid @RequestBody AvailabilityRequest availabilityRequest) {

        if (!availabilityService.saveAll(availabilityRequest)) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed to update!"));
        }

        return ResponseEntity.ok().body(new ApiResponse(true, "Update availability successful!"));
    }
}
