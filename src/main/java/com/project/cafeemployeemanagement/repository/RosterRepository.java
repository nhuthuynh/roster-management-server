package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Long> {
    @Query("SELECT r FROM Roster AS r WHERE r.fromDate >= :fromDate and r.fromDate <= :toDate and r.employee.id = :shopOwnerId")
    Roster findByDatesAndShopOwnerId(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, @Param("shopOwnerId") Long shopOwnerId);

    @Query("SELECT r FROM Roster AS r WHERE r.toDate >= :currentDate and r.employee.id = :shopOwnerId")
    List<Roster> findByToDateAfterAndEmployee(@Param("currentDate") LocalDate currentDate , @Param("shopOwnerId") Long shopOwnerId);
}
