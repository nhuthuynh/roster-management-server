package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.exception.AppException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    EmployeeService employeeService;

    @Transactional
    public boolean createRoster(RosterRequest rosterRequest) {
        Roster newRoster = new Roster();
        Employee shopOwner = employeeService.loadById(rosterRequest.getShopOwnerId());

        newRoster.setFromDate(rosterRequest.getFromDate());
        newRoster.setToDate(rosterRequest.getToDate());
        newRoster.setCreatedDate(rosterRequest.getCreatedDate());

        List<Shift> shifts = new ArrayList<>();

        rosterRequest.getShiftList().forEach(shiftRequest -> {
            Shift newShift = new Shift(newRoster, shiftRequest.getDate(), shiftRequest.getNote());
            shiftRequest.getEmployeeShifts().forEach(employeeShiftRequest -> {
                EmployeeShift employeeShift = new EmployeeShift(employeeShiftRequest.getStart(), employeeShiftRequest.getEnd(), employeeShiftRequest.getNote());
                Employee employee = employeeRepository.findById(employeeShiftRequest.getEmployeeId()).orElseThrow(() -> new AppException("Cannot find employee"));
                newShift.addEmployeeShift(employeeShift, employee);
            });
            shifts.add(newShift);
        });
        newRoster.setShiftList(shifts);
        newRoster.setEmployee(shopOwner);
        rosterRepository.save(newRoster);
        return true;
    }

    public void deleteRoster(Long rosterId) {
        if (rosterId != null) {
            Roster roster = rosterRepository.findById(rosterId).orElseThrow(() -> new AppException("Roster is not existed!"));
            rosterRepository.delete(roster);
        }
    }

    public RosterResponse loadRoster(Roster roster) {
        return ModelMapper.mapRosterToRosterResponse(roster);
    }

    public RosterResponse loadRosterByDatesAndShopOwner(final Date fromDate, final Date toDate, final Long shopOwnerId) {
        Roster roster = rosterRepository.findByDates(fromDate, toDate, shopOwnerId);
        if (roster == null) {
            return new RosterResponse();
        }
        return loadRoster(roster);
    }
}
