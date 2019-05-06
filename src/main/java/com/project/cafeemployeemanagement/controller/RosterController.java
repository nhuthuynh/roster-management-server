package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.model.Roster;
import com.project.cafeemployeemanagement.payload.ApiResponse;
import com.project.cafeemployeemanagement.payload.RosterRequest;
import com.project.cafeemployeemanagement.payload.RosterResponse;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.repository.RosterRepository;
import com.project.cafeemployeemanagement.service.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/roster")
public class RosterController {

    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    EmployeeRepository employeeRepository;

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
    public @ResponseBody
    RosterResponse loadRoster(@RequestParam("from") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate, @RequestParam("to") @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate, @RequestParam("shopOwnerId") Long employeeId) {
        Roster roster = rosterRepository.findByDates(fromDate, toDate, employeeId);
        if (roster == null) {
            return new RosterResponse();
        }
        return rosterService.loadRoster(roster);
    }
}
