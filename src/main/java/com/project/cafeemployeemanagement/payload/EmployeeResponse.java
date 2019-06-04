package com.project.cafeemployeemanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.util.CustomDateDeserialize;
import com.project.cafeemployeemanagement.util.LocalDateDeserialize;

import java.time.LocalDate;

public class EmployeeResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long shopOwnerId;

    @JsonDeserialize(using = LocalDateDeserialize.class)
    @JsonFormat(pattern= Constants.DATE_FORMAT)
    private LocalDate joinedDate;

    private String type;

    private String role;

    private String phoneNumber;

    private boolean isResigned;

    public EmployeeResponse(Long id, String firstName, String lastName, String email, LocalDate joinedDate, String type, String role, Long shopOwnerId, String phoneNumber, boolean isResigned) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinedDate = joinedDate;
        this.type = type;
        this.role = role;
        this.shopOwnerId = shopOwnerId;
        this.phoneNumber = phoneNumber;
        this.isResigned = isResigned;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getShopOwnerId() {
        return shopOwnerId;
    }

    public void setShopOwnerId(Long shopOwnerId) {
        this.shopOwnerId = shopOwnerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isResigned() {
        return isResigned;
    }

    public void setResigned(boolean resigned) {
        isResigned = resigned;
    }
}
