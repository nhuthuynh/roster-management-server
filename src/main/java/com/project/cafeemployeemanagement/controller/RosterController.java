package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.model.Employee;
import com.project.cafeemployeemanagement.model.Roster;
import com.project.cafeemployeemanagement.payload.ApiResponse;
import com.project.cafeemployeemanagement.payload.EmployeeResponse;
import com.project.cafeemployeemanagement.payload.RosterRequest;
import com.project.cafeemployeemanagement.payload.RosterResponse;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.repository.RosterRepository;
import com.project.cafeemployeemanagement.service.RosterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(RosterController.class);

    @PostMapping("/create")
    public ResponseEntity<?> createRoster(@Valid @RequestBody RosterRequest rosterRequest) {
        if (rosterService.createRoster(rosterRequest))
            return ResponseEntity.ok().body(new ApiResponse(true, "Roster created sucessfully!"));
        return  ResponseEntity.badRequest().body(new ApiResponse(false, "create roster failed!"));
    }

    @GetMapping("/load")
    public @ResponseBody
    RosterResponse loadRoster(@RequestParam("from") @DateTimeFormat(pattern="dd-MM-yyyy") Date fromDate) {
        Roster roster = rosterRepository.findByStartDate(fromDate);
        if (roster == null) {
            return new RosterResponse();
        }
        return rosterService.loadRoster(roster);
    }
}
