package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.model.EmployeeType;
import com.project.cafeemployeemanagement.model.EmployeeTypeValues;
import com.project.cafeemployeemanagement.repository.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeTypeService {
    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Transactional
    public void initEmployeeTypes() {
        if (employeeTypeRepository.findAll().isEmpty()) {
            employeeTypeRepository.save(new EmployeeType(EmployeeTypeValues.FULL_TIME));
            employeeTypeRepository.save(new EmployeeType(EmployeeTypeValues.PART_TIME));
            employeeTypeRepository.save(new EmployeeType(EmployeeTypeValues.PART_TIME_STUDENT));
        }
    }
}
