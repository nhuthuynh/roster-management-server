package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.EmployeeType;
import com.project.cafeemployeemanagement.model.EmployeeTypeValues;
import com.project.cafeemployeemanagement.model.Role;
import com.project.cafeemployeemanagement.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {
    Optional<EmployeeType> findByType(EmployeeTypeValues employeeTypeValues);
}
