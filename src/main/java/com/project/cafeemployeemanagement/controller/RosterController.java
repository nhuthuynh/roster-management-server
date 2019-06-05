package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.payload.ApiResponse;
import com.project.cafeemployeemanagement.payload.RosterRequest;
import com.project.cafeemployeemanagement.repository.RosterRepository;
import com.project.cafeemployeemanagement.service.EmployeeService;
import com.project.cafeemployeemanagement.service.RosterService;
import com.project.cafeemployeemanagement.util.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/roster")
public class RosterController {

    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RosterService rosterService;

    @PostMapping("/create")
    public ResponseEntity<?> createRoster(@Valid @RequestBody RosterRequest rosterRequest) {
        rosterService.deleteRoster(rosterRequest.getId());
        if (rosterService.createRoster(rosterRequest))
            return ResponseEntity.ok().body(new ApiResponse(true, "Roster created successfully!"));
        return ResponseEntity.badRequest().body(new ApiResponse(false, "create roster failed!"));
    }

    @GetMapping("/load")
    public ResponseEntity<?> loadRoster(@RequestParam("from") @DateTimeFormat(pattern = Constants.DATE_FORMAT) String fromDate, @RequestParam("to") @DateTimeFormat(pattern = Constants.DATE_FORMAT) String toDate, @RequestParam("shopOwnerId") Long shopOwnerId) {
        return ResponseEntity.ok().body(rosterService.loadRosterByDatesAndShopOwner(utils.parseLocalDate(fromDate), utils.parseLocalDate(toDate), shopOwnerId));
    }

    @GetMapping("/shopOwner/{shopOwnerId}/employees")
    public ResponseEntity<?> loadEmployees(@PathVariable final long shopOwnerId) {
        return ResponseEntity.ok(employeeService.findByShopOwnerIdAndResignedIs(shopOwnerId, false));
    }

    @GetMapping("/shopOwner/{shopOwnerId}/latest")
    public ResponseEntity<?> findLatestRosterByToDateAndShopOwner(@PathVariable final long shopOwnerId) {
        return ResponseEntity.ok().body(rosterService.findLatestRosterResponseByToDateAndShopOwner(shopOwnerId));
    }
}
