package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.model.EmployeeType;
import com.project.cafeemployeemanagement.model.EmployeeTypeValues;
import com.project.cafeemployeemanagement.model.RoleName;
import org.springframework.stereotype.Service;

@Service
public class UtilsService {

    public boolean isValidPhonumber(String phoneNumber) {

        return true;
    }

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
}
