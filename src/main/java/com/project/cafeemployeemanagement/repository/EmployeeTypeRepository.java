package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {


}
