package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.model.EmployeeTypeValues;
import com.project.cafeemployeemanagement.model.RoleName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UtilsService {

    @Value("${app.web.protocol}")
    private String protocol;

    @Value("${app.mail.client.port}")
    private String clientPort;

    public RoleName getRoleName(String name) {
        if (name.equalsIgnoreCase(RoleName.ROLE_ADMIN.toString())) {
            return RoleName.ROLE_ADMIN;
        } else if (name.equalsIgnoreCase(RoleName.ROLE_MANAGER.toString())) {
            return RoleName.ROLE_MANAGER;
        }

        return RoleName.ROLE_EMPLOYEE;
    }

    public EmployeeTypeValues getEmployeeType(String type) {
        if (type.equalsIgnoreCase(EmployeeTypeValues.PART_TIME.toString())) {
            return EmployeeTypeValues.PART_TIME;
        } else if (type.equalsIgnoreCase(EmployeeTypeValues.PART_TIME_STUDENT.toString())) {
            return EmployeeTypeValues.PART_TIME_STUDENT;
        }

        return EmployeeTypeValues.FULL_TIME;
    }

    public String getAppUrl(HttpServletRequest request) {
        return protocol + request.getServerName() + ":" + clientPort + request.getContextPath();
    }
}
