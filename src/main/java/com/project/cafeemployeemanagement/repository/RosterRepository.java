package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Long> {
    @Query("SELECT r FROM Roster AS r WHERE r.fromDate >= :fromDate and r.fromDate <= :toDate and r.employee.id = :shopOwnerId")
    Roster findByDates(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, @Param("shopOwnerId") Long shopOwnerId);
}
