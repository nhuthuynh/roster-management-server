package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.payload.ApiResponse;
import com.project.cafeemployeemanagement.payload.EmployeeResponse;
import com.project.cafeemployeemanagement.payload.ResignEmployeeRequest;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.security.CurrentUser;
import com.project.cafeemployeemanagement.security.UserPrincipal;
import com.project.cafeemployeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    List<EmployeeResponse> loadEmployees(@RequestParam Long shopOwnerId) {
        return employeeRepository.findByShopOwnerId(shopOwnerId)
                .stream()
                .map(
                        employee
                                ->
                                new EmployeeResponse(employee.getId(),
                                        employee.getFirstName(),
                                        employee.getLastName(),
                                        employee.getEmail(),
                                        employee.getJoinedDate(),
                                        employee.getEmployeeType().getType().name(),
                                        employee.getRole().getName().name(),
                                        employee.getShopOwnerId(),
                                        employee.getPhoneNumber(),
                                        employee.isResigned())).collect(Collectors.toList());
    }

    @GetMapping("/me")
    public EmployeeResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        EmployeeResponse employeeResponse =
                new EmployeeResponse(currentUser.getId(),
                        currentUser.getFirstName(),
                        currentUser.getLastName(),
                        currentUser.getEmail(),
                        currentUser.getJoinedDate(),
                        currentUser.getType(),
                        currentUser.getRole(),
                        currentUser.getShopOwnerId(),
                        currentUser.getPhoneNumber(),
                        currentUser.isResigned()
                        );
        return employeeResponse;
    }

    @PostMapping("/resign")
    public ApiResponse resignEmployees(@Valid @RequestBody ResignEmployeeRequest resignEmployeeRequest) {
        if(!employeeService.resignEmployees(resignEmployeeRequest)) {
            return new ApiResponse(false, "Fail to update employees!");
        }

        return new ApiResponse(true, "Employees updated!");
    }
}
