package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.exception.AppException;

import com.project.cafeemployeemanagement.model.*;
import com.project.cafeemployeemanagement.payload.*;
import com.project.cafeemployeemanagement.repository.EmployeeTypeRepository;
import com.project.cafeemployeemanagement.repository.PasswordResetTokenRepository;
import com.project.cafeemployeemanagement.repository.RoleRepository;

import com.project.cafeemployeemanagement.security.UserPrincipal;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.UUID;

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

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private MailService mailService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) throw new UsernameNotFoundException("Email is not found!");
        return UserPrincipal.create(employee);
    }

    public Employee loadByEmail(String employeeEmail) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(employeeEmail);
        if (employee == null) throw new UsernameNotFoundException("Email is not found!");
        return employee;
    }

    public Employee loadById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new AppException("Employee is not found"));
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
        Employee emp = new Employee(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getPhoneNumber(), signUpRequest.getEmail(), password, 0, signUpRequest.getShopOwnerId(), false);

        emp.setRole(role);
        emp.setEmployeeType(empType);

        return employeeRepository.save(emp);
    }

    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Transactional
    public boolean resignEmployees(ResignEmployeeRequest resignEmployeeRequest) {
        int numberOfUpdatedEmployees = employeeRepository.resignEmployees(resignEmployeeRequest.getEmployeesIdList());
        if(resignEmployeeRequest.getEmployeesIdList().size() == numberOfUpdatedEmployees) {
            return true;
        }
        return false;
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) authService.getAuthenticationFromAuthenticatedUser().getPrincipal();
        Employee employee = loadById(userPrincipal.getId());
        if (isValidPassword(employee, changePasswordRequest.getCurrentPassword())) {
            employee.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            employeeRepository.save(employee);
        } else {
            throw new AppException("Password is not correct!");
        }
    }

    public void savePassword(ChangePasswordWithTokenRequest changePasswordWithTokenRequest) {
        Employee employee = loadById(changePasswordWithTokenRequest.getEmployeeId());
        employee.setPassword(passwordEncoder.encode(changePasswordWithTokenRequest.getPassword()));
        employeeRepository.save(employee);
    }

    private boolean isValidPassword(final Employee employee, final String password) {
        return passwordEncoder.matches(password, employee.getPassword());
    }

    public ProfileResponse loadProfile(final long employeeId) {
        Employee employee = loadById(employeeId);
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(employeeId);
        profileResponse.setEmail(employee.getEmail());
        profileResponse.setFirstName(employee.getFirstName());
        profileResponse.setLastName(employee.getLastName());
        profileResponse.setPhoneNumber(employee.getPhoneNumber());
        profileResponse.setAddress(employee.getAddress());
        profileResponse.setType(employee.getEmployeeType().getType().name());
        return profileResponse;
    }

    public void updateProfile(ProfileRequest profileRequest) {
        Employee employee = employeeRepository.findById(profileRequest.getId())
                .orElseThrow(() -> new AppException("Employee is not found!"));
        employee.setEmail(profileRequest.getEmail());
        employee.setAddress(profileRequest.getAddress());
        employee.setFirstName(profileRequest.getFirstName());
        employee.setLastName(profileRequest.getLastName());
        employee.setPhoneNumber(profileRequest.getPhoneNumber());
        employeeRepository.save(employee);
    }

    public void createPasswordResetTokenForEmployee(Employee employee, String token) {
        passwordResetTokenRepository.save(new PasswordResetToken(token, employee));
    }

    public void resetPassword(HttpServletRequest httpServletRequest, String employeeEmail) {
        Employee employee = loadByEmail(employeeEmail);
        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForEmployee(employee, token);
        mailService.getJavaMailSender().send(mailService.constructResetPasswordEmail(utilsService.getAppUrl(httpServletRequest), token, employee));
    }

}
