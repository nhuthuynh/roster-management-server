package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.EmployeeShift;
import com.project.cafeemployeemanagement.model.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeShiftRepository extends JpaRepository<EmployeeShift, Long> {

    @Query("SELECT es FROM EmployeeShift AS es WHERE es.employee.id = :employeeId and es.shift.date < :date")
    List<EmployeeShift> findByEmployeeAndShift(@Param("employeeId") Long employeeId, @Param("date") Date date);
}
