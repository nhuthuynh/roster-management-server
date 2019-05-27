package com.project.cafeemployeemanagement.repository;


import com.project.cafeemployeemanagement.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    @Query("SELECT a FROM Availability AS a WHERE a.employee.id = :employeeId")
    List<Availability> findAllByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT a from Availability AS a WHERE a.employee.id = :employeeId AND :currentDate > a.effectiveDate")
    List<Availability> findAllByEmployeeIdAndEffectiveDate(@Param("employeeId") Long employeeId, @Param("currentDate") Date currentDate);
}
