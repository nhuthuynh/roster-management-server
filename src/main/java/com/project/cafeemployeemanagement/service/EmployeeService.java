package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.exception.AppException;
import com.project.cafeemployeemanagement.model.*;
import com.project.cafeemployeemanagement.repository.EmployeeTypeRepository;
import com.project.cafeemployeemanagement.repository.RoleRepository;
import com.project.cafeemployeemanagement.security.UserPrincipal;
import com.project.cafeemployeemanagement.payload.SignUpRequest;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + email));
        return UserPrincipal.create(employee);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found with id: " + id));
        return UserPrincipal.create(emp);
    }

    @Transactional
    public Employee signUpUser(SignUpRequest signUpRequest) {
        EmployeeType empType = employeeTypeRepository
                .findByType(utilsService.getEmployeeType(signUpRequest.getType()))
                .orElseThrow(()-> new AppException("Employee Type has not defined!"));
        Role role = roleRepository
                .findByName(utilsService.getRoleName(signUpRequest.getRole()))
                .orElseThrow(()-> new AppException("Employee Role has not defined!"));
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        Employee emp = new Employee(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getPhoneNumber(), signUpRequest.getEmail(), password, 0, signUpRequest.getShopOwnerId());

        emp.setRole(role);
        emp.setEmployeeType(empType);

        return employeeRepository.save(emp);
    }

    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

}
