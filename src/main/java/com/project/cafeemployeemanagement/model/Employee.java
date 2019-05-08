package com.project.cafeemployeemanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private String phoneNumber;

    private String email;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_type_id")
    private EmployeeType employeeType;

    private double hourlyRate;

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<EmployeeShift> employeeShifts = new ArrayList<>();

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Availability> availabilityList = new ArrayList<>();

    private Long shopOwnerId;

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Roster> rostersList = new ArrayList<>();

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<LeaveRequest> leaveRequests = new ArrayList<>();

    private int annualLeaveBalance = 0;

    private boolean isResigned;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;

    public List<Roster> getRostersList() {
        return rostersList;
    }

    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public Long getShopOwnerId() {
        return shopOwnerId;
    }

    public Employee() {}

    public Employee(String firstName, String lastName, String phoneNumber, String email, String password, double hourlyRate, Long shopOwnerId, boolean isResigned) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hourlyRate = hourlyRate;
        this.isResigned = isResigned;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.joinedDate = new Date();
        this.shopOwnerId = shopOwnerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public List<EmployeeShift> getEmployeeShifts() {
        return employeeShifts;
    }

    public void setEmployeeShifts(List<EmployeeShift> employeeShifts) {
        this.employeeShifts = employeeShifts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRostersList(Roster roster) {
        this.rostersList.add(roster);
        roster.setEmployee(this);
    }

    public void removeRoster(Roster roster) {
        this.rostersList.remove(roster);
        roster.setEmployee(null);
    }

    public void setShopOwnerId(Long shopOwnerId) {
        this.shopOwnerId = shopOwnerId;
    }

    public void addEmployeeShift(EmployeeShift employeeShift, Employee employee) {
        employeeShift.setEmployee(employee);
        employeeShift.setEmployee(this);
        employeeShifts.add(employeeShift);
    }

    public void removeEmployeeShift(EmployeeShift employeeShift) {
        employeeShifts.remove(employeeShift);
        employeeShift.setShift(null);
        employeeShift.setEmployee(null);
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList.clear();

        if (availabilityList == null || availabilityList.isEmpty()) {
            return;
        }

        availabilityList.forEach(availability -> availability.setEmployee(this));
        this.availabilityList.addAll(availabilityList);
    }

    public void addAvailability(Availability availability) {
        this.availabilityList.add(availability);
        availability.setEmployee(this);
    }

    public void removeAvailability(Availability availability) {
        this.availabilityList.remove(availability);
        availability.setEmployee(null);
    }

    public boolean isResigned() {
        return isResigned;
    }

    public void setResigned(boolean resigned) {
        isResigned = resigned;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAnnualLeaveBalance() {
        return annualLeaveBalance;
    }

    public void setAnnualLeaveBalance(int annualLeaveBalance) {
        this.annualLeaveBalance = annualLeaveBalance;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public void addLeaveRequests(LeaveRequest leaveRequest) {
        this.leaveRequests.add(leaveRequest);
        leaveRequest.setEmployee(this);
    }

    public void removeLeaveRequests(LeaveRequest leaveRequest) {
        this.leaveRequests.remove(leaveRequest);
        leaveRequest.setEmployee(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee )) return false;
        return id != null && id.equals(((Employee) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }

}
