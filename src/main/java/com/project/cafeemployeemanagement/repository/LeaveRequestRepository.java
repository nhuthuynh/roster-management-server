package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.LeaveRequest;
import com.project.cafeemployeemanagement.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("SELECT l FROM LeaveRequest as l WHERE l.status = :status")
    List<LeaveRequest> findByStatus(@Param("status") LeaveStatus status);

    @Query("SELECT l FROM LeaveRequest as l WHERE l.updatedManagerId = :managerId")
    List<LeaveRequest> findByUpdatedManagerId(@Param("managerId") Long managerId);

    @Query("SELECT l FROM LeaveRequest as l WHERE l.employee.id = :employeeId")
    List<LeaveRequest> findByEmployee(@Param("employeeId") long employeeId);

    @Query("SELECT l FROM LeaveRequest as l WHERE l.employee.id = :employeeId and l.status = :status")
    List<LeaveRequest> findByEmployeeAndStatus(@Param("employeeId") Long employeeId, @Param("status") LeaveStatus status);

    @Query("SELECT l FROM LeaveRequest as l WHERE l.employee.shopOwnerId = :shopOwnerId and l.status = :status")
    List<LeaveRequest> findByShopOwnerIdAndStatus(@Param("shopOwnerId") long shopOwnerId, @Param("status") LeaveStatus status);
}
