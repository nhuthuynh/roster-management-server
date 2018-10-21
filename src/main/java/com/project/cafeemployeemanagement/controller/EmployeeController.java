package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.payload.EmployeeResponse;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    private static final Logger logger = LoggerFactory.getLogger(RosterController.class);

    @GetMapping("/load")
    public @ResponseBody
    List<EmployeeResponse> loadEmployees() {
        return employeeRepository.findAll().stream().map(employee -> new EmployeeResponse(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getJoinedDate(), employee.getEmployeeType(), employee.getRole())).collect(Collectors.toList());
    }
}
