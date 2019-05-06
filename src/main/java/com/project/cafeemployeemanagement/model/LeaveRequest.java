package com.project.cafeemployeemanagement.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="leave_request")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private LeaveStatus leaveStatus;

    private LeaveType leaveType;

    @ManyToMany(mappedBy = "leave_requests")
    private Set<Employee> employees = new HashSet<>();

    private Long updatedByManagerId;

    private String note;

    private Date createdDate;

    private Date updatedDate;
}
