package com.project.cafeemployeemanagement.util;

import com.project.cafeemployeemanagement.model.EmployeeShift;
import com.project.cafeemployeemanagement.model.Roster;
import com.project.cafeemployeemanagement.payload.RosterResponse;
import com.project.cafeemployeemanagement.payload.ShiftResponse;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {

    public static RosterResponse mapRosterToRosterResponse(Roster roster) {
        RosterResponse response = new RosterResponse();
        List<ShiftResponse> shiftResponses = new ArrayList<>();

        response.setFromDate(utils.formatDate(roster.getFromDate()));
        response.setToDate(utils.formatDate(roster.getToDate()));
        response.setId(roster.getId());

        roster.getShiftList().forEach(shift -> {
            String dateString = utils.formatDate(shift.getDate());
            for (EmployeeShift employeeShift : shift.getEmployeeShifts()) {
                ShiftResponse shiftResponse = new ShiftResponse();
                shiftResponse.setId(employeeShift.getId());
                shiftResponse.setStart(String.format("%s %s", dateString, utils.formatTime(employeeShift.getStartTime())));
                shiftResponse.setEnd(String.format("%s %s", dateString, utils.formatTime(employeeShift.getEndTime())));
                shiftResponse.setShiftId(employeeShift.getShift().getId());
                shiftResponse.setEmployeeId(employeeShift.getEmployee().getId());
                shiftResponse.setTitle(String.format("%s %s", employeeShift.getEmployee().getFirstName(), employeeShift.getEmployee().getLastName()));
                shiftResponses.add(shiftResponse);
            }
        });
        response.setShiftList(shiftResponses);

        return response;
    }

}
