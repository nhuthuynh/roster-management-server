package com.project.cafeemployeemanagement.controller;

import com.project.cafeemployeemanagement.payload.ApiResponse;
import com.project.cafeemployeemanagement.payload.JwtAuthenticationResponse;
import com.project.cafeemployeemanagement.payload.LoginRequest;
import com.project.cafeemployeemanagement.payload.SignUpRequest;
import com.project.cafeemployeemanagement.service.AuthService;
import com.project.cafeemployeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authService.authenticateUser(loginRequest);
        String jwt = authService.getTokenFromAuthenticateUser(authentication);
        if (authentication == null || jwt.isEmpty())
            return new ResponseEntity<>(new ApiResponse (false, "Email or password is invalid!"), HttpStatus.BAD_REQUEST);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(HttpServletRequest httpServletRequest,  @Valid @RequestBody SignUpRequest signUpRequest) {
        if (employeeService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, "Email is existed!"));
        }
        employeeService.signUpUser(httpServletRequest, signUpRequest);
        //URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{email}").buildAndExpand(emp.getEmail()).toUri();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User registered successfully!"));
    }
}
