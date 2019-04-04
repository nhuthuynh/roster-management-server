package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.model.*;
import com.project.cafeemployeemanagement.payload.EmployeeResponse;
import com.project.cafeemployeemanagement.payload.SignUpRequest;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.security.CurrentUser;
import com.project.cafeemployeemanagement.security.UserPrincipal;
import com.project.cafeemployeemanagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/load")
    public @ResponseBody
    List<EmployeeResponse> loadEmployees() {
        return employeeRepository.findAll().stream().map(employee -> new EmployeeResponse(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getJoinedDate(), employee.getEmployeeType().getType().name(), employee.getRole().getName().name(), employee.getShopOwnerId())).collect(Collectors.toList());
    }

    @GetMapping("/me")
    public EmployeeResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        EmployeeResponse employeeResponse =
                new EmployeeResponse(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), new Date(),  currentUser.getType(), currentUser.getRole(), currentUser.getShopOwnerId());
        return employeeResponse;
    }

}
