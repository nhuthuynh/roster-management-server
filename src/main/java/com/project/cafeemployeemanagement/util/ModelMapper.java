package com.project.cafeemployeemanagement.util;

import com.project.cafeemployeemanagement.model.*;
import com.project.cafeemployeemanagement.payload.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {

    public static RosterResponse mapRosterToRosterResponse(Roster roster) {
        RosterResponse response = new RosterResponse();
        List<ShiftResponse> shiftResponses = new ArrayList<>();

        response.setFromDate(roster.getFromDate());
        response.setToDate(roster.getToDate());
        response.setId(roster.getId());

        roster.getShiftList().forEach(shift -> {
            for (EmployeeShift employeeShift : shift.getEmployeeShifts()) {
                ShiftResponse shiftResponse = new ShiftResponse();
                shiftResponse.setId(employeeShift.getId());
                shiftResponse.setStart(employeeShift.getStart());
                shiftResponse.setEnd(employeeShift.getEnd());
                shiftResponse.setShiftId(employeeShift.getShift().getId());
                shiftResponse.setEmployeeId(employeeShift.getEmployee().getId());
                shiftResponse.setTitle(String.format("%s %s", employeeShift.getEmployee().getFirstName(), employeeShift.getEmployee().getLastName()));
                shiftResponses.add(shiftResponse);
            }
        });
        response.setShiftList(shiftResponses);

        return response;
    }

    public static List<LeaveRequestsResponse> mapLeaveRequestsToLeaveRequestsResponse(List<LeaveRequest> leaveRequests) {

        List<LeaveRequestsResponse> leaveRequestsResponses = leaveRequests.stream().map(leaveRequest -> {
            LeaveRequestsResponse leaveRequestsResponse = new LeaveRequestsResponse();
            leaveRequestsResponse.setId(leaveRequest.getId());
            leaveRequestsResponse.setFromDate(utils.formatDate(leaveRequest.getFromDate()));
            leaveRequestsResponse.setToDate(utils.formatDate(leaveRequest.getToDate()));
            leaveRequestsResponse.setNote(leaveRequest.getNote());
            leaveRequestsResponse.setEmployeeId(leaveRequest.getEmployee().getId());
            leaveRequestsResponse.setEmployeeFirstName(leaveRequest.getEmployee().getFirstName());
            leaveRequestsResponse.setEmployeeLastName(leaveRequest.getEmployee().getLastName());
            leaveRequestsResponse.setEmployeePhoneNumber(leaveRequest.getEmployee().getPhoneNumber());
            return leaveRequestsResponse;
        }).collect(Collectors.toList());

        return leaveRequestsResponses;
    }

    public static List<AvailabilityResponse> mapAvailabilitiesToResponse(List<Availability> availabilities) {
        List<AvailabilityResponse> availabilityResponseList = new ArrayList<>();

        availabilities.forEach(availability -> {
            availabilityResponseList.add(new AvailabilityResponse(
                    availability.getId(),
                    availability.getDay(),
                    availability.getStartHour(),
                    availability.getStartMinute(),
                    availability.getEndHour(),
                    availability.getEndMinute(),
                    availability.isAvailable()));
        });

        return availabilityResponseList;
    }

    public static List<EmployeeResponse> mapEmployeesToResponse(List<Employee> employees) {
        return employees.stream()
                .map(employee ->
                        new EmployeeResponse(employee.getId(),
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getEmail(),
                                employee.getJoinedDate(),
                                employee.getEmployeeType().getType().name(),
                                employee.getRole().getName().name(),
                                employee.getShopOwnerId(),
                                employee.getPhoneNumber(),
                                employee.isResigned())
                ).collect(Collectors.toList());
    }

}
