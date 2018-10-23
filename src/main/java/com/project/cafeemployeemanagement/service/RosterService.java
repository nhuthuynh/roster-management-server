package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.model.Employee;
import com.project.cafeemployeemanagement.model.EmployeeShift;
import com.project.cafeemployeemanagement.model.Roster;
import com.project.cafeemployeemanagement.model.Shift;
import com.project.cafeemployeemanagement.payload.RosterRequest;
import com.project.cafeemployeemanagement.payload.RosterResponse;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.repository.EmployeeShiftRepository;
import com.project.cafeemployeemanagement.repository.RosterRepository;
import com.project.cafeemployeemanagement.repository.ShiftRepository;
import com.project.cafeemployeemanagement.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class RosterService {
    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    EmployeeShiftRepository employeeShiftRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    private static final Logger logger = LoggerFactory.getLogger(RosterService.class);

    @Transactional
    public boolean createRoster(RosterRequest rosterRequest) {
        Roster newRoster = new Roster();
        Roster oldRoster = rosterRepository.findById(rosterRequest.getId()).get();

        if (oldRoster != null) {
            rosterRepository.delete(oldRoster);
        }

        newRoster.setFromDate(rosterRequest.getFromDate());
        newRoster.setToDate(rosterRequest.getToDate());
        newRoster.setCreatedDate(rosterRequest.getCreatedDate());

        List<Shift> shifts = new ArrayList<>();

        rosterRequest.getShiftList().forEach(shiftRequest -> {
            Shift newShift = new Shift(newRoster, shiftRequest.getDate(), shiftRequest.getNote());
            shiftRequest.getEmployeeShifts().forEach(employeeShiftRequest -> {
                EmployeeShift employeeShift = new EmployeeShift(employeeShiftRequest.getStartTime(), employeeShiftRequest.getEndTime(), employeeShiftRequest.getNote());
                Employee employee = employeeRepository.findById(employeeShiftRequest.getEmployeeId()).get();
                newShift.addEmployeeShift(employeeShift, employee);
            });
            shifts.add(newShift);
        });
        newRoster.setShiftList(shifts);
        rosterRepository.save(newRoster);
        return true;
    }

    public RosterResponse loadRoster(Roster roster) {
        return ModelMapper.mapRosterToRosterResponse(roster);
    }
}
