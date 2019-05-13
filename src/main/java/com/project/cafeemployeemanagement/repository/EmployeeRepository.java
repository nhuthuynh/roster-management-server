package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.Employee;
import com.project.cafeemployeemanagement.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);

    Boolean existsByEmail(String email);

    List<Employee> findByShopOwnerIdOrderByJoinedDateDesc(Long shopOwnerId);

    List<Employee> findByShopOwnerIdAndIsResignedOrderByJoinedDateDesc(Long shopOwnerId, boolean isResigned);

    @Modifying
    @Query("UPDATE Employee AS e SET e.isResigned = true WHERE e.id in :employeeList")
    int resignEmployees(@Param("employeeList") List<Long> employeeList);
}
