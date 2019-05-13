package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.LeaveRequest;
import com.project.cafeemployeemanagement.model.LeaveStatus;
import com.project.cafeemployeemanagement.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("SELECT l FROM LeaveRequest as l WHERE l.employee.id = :employeeId")
    List<LeaveRequest> findByEmployee(@Param("employeeId") long employeeId);

    @Query("SELECT l FROM LeaveRequest as l WHERE l.employee.id = :employeeId and l.status = :status")
    List<LeaveRequest> findByEmployeeAndStatus(@Param("employeeId") Long employeeId, @Param("status") LeaveStatus status);

    @Query("SELECT l FROM LeaveRequest as l WHERE l.employee.shopOwnerId = :shopOwnerId and l.status = :status")
    List<LeaveRequest> findByShopOwnerIdAndStatus(@Param("shopOwnerId") long shopOwnerId, @Param("status") LeaveStatus status);

    @Query("SELECT l FROM LeaveRequest as l WHERE l.employee.shopOwnerId = :shopOwnerId and l.employee.role.name = :roleName  and l.status = :status")
    List<LeaveRequest> findByShopOwnerIdAndStatusAndIsEmployeeRole(@Param("shopOwnerId") long shopOwnerId, @Param("roleName") RoleName roleName, @Param("status") LeaveStatus status);

    LeaveRequest findByFromDate(Date fromDate);
}
