package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.payload.*;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.security.CurrentUser;
import com.project.cafeemployeemanagement.security.JwtTokenProvider;
import com.project.cafeemployeemanagement.security.UserPrincipal;
import com.project.cafeemployeemanagement.service.AuthService;
import com.project.cafeemployeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AuthService authService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("/load")
    public ResponseEntity<?> loadEmployees(@RequestParam Long shopOwnerId) {
        return ResponseEntity.ok(employeeService.findByShopOwnerId(shopOwnerId));
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
        if (!employeeService.resignEmployees(resignEmployeeRequest)) {
            return new ApiResponse(false, "Fail to update employees!");
        }

        return new ApiResponse(true, "Employees updated!");
    }

    @PostMapping("/resetPassword")
    public ApiResponse resetPassword(final HttpServletRequest httpServletRequest, @RequestBody final ResetPasswordRequest resetPasswordRequest) {
        employeeService.resetPassword(httpServletRequest, resetPasswordRequest.getEmail());
        return new ApiResponse(true, "Please check your email!");
    }

    @PostMapping("/changePassword")
    public ApiResponse changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        employeeService.changePassword(changePasswordRequest);
        return new ApiResponse(true, "Password is changed!");
    }

    @PostMapping("/updateProfile")
    public ApiResponse updateProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        employeeService.updateProfile(profileRequest);
        return new ApiResponse(true, "Profile is updated!");
    }

    @GetMapping("/loadProfile")
    public ProfileResponse loadProfile(@RequestParam("employeeId") long employeeId) {
        return employeeService.loadProfile(employeeId);
    }

    @PostMapping("/changePasswordWithToken")
    public ResponseEntity<?> changePasswordWithToken(@Valid @RequestBody ChangePasswordWithTokenRequest changePasswordWithTokenRequest) {
        if (authService.authenticateResetPasswordTokenOfEmployee(changePasswordWithTokenRequest.getToken(), changePasswordWithTokenRequest.getEmployeeId())) {
            employeeService.savePassword(changePasswordWithTokenRequest);
            return ResponseEntity.ok().body(new ApiResponse(true, "Reset password successfully!"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed to reset password!"));
    }
}
