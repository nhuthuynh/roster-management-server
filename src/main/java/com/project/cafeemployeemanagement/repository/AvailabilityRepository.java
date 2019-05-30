package com.project.cafeemployeemanagement.repository;


import com.project.cafeemployeemanagement.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    @Query("SELECT a FROM Availability AS a WHERE :date > a.effectiveDate AND :employeeId = a.employee.id")
    List<Availability> findByEffectiveDateAfterAndEmployeeId(@Param("date") Date date, @Param("employeeId") Long employeeId);

    @Query("SELECT a FROM Availability AS a WHERE :employeeId = a.employee.id")
    List<Availability> findByEmployeeId(@Param("employeeId") Long employeeId);

}
