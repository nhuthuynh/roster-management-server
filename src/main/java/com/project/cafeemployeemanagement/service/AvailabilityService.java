package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.exception.AppException;
import com.project.cafeemployeemanagement.model.Availability;
import com.project.cafeemployeemanagement.model.Employee;
import com.project.cafeemployeemanagement.model.Roster;
import com.project.cafeemployeemanagement.payload.AvailabilityRequest;
import com.project.cafeemployeemanagement.payload.AvailabilityResponse;
import com.project.cafeemployeemanagement.repository.AvailabilityRepository;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RosterService rosterService;

    @Transactional
    public List<Availability> findByEmployeeId(final Long employeeId) {
        List<Availability> availabilities = availabilityRepository.findByEmployeeId(employeeId);

        if (availabilities.size() == 0) {
            return createDefault(employeeId);
        }

        return availabilities;
    }

    public List<Availability> findByEffectiveDateAfterAndEmployeeId(final Date effectiveDate, final Long employeeId) {
        return availabilityRepository.findByEffectiveDateAfterAndEmployeeId(effectiveDate, employeeId);
    }

    public List<AvailabilityResponse> findResponsesByEffectiveDateAfterAndEmployeeId(final Date effectiveDate, final Long employeeId) {
        return ModelMapper.mapAvailabilitiesToResponse(findByEffectiveDateAfterAndEmployeeId(effectiveDate, employeeId));
    }

    public List<AvailabilityResponse> findResponsesByEmployeeId(final Long employeeId) {
        return ModelMapper.mapAvailabilitiesToResponse(findByEmployeeId(employeeId));
    }


    private List<Availability> createDefault(Long employeeId) {
        List<Availability> availabilities = new ArrayList<>();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new AppException("Cannot find employee!"));

        for (int eachDayIndex = 0; eachDayIndex < Constants.DEFAULT_AVAILABILITY_DAYS.length; eachDayIndex++) {
            availabilities.add(new Availability(
                    Constants.DEFAULT_AVAILABILITY_DAYS[eachDayIndex],
                    Constants.DEFAULT_START_HOUR,
                    Constants.DEFAULT_START_MINUTE,
                    Constants.DEFAULT_END_HOUR,
                    Constants.DEFAULT_END_MINUTE,
                    true,
                    new Date()));
        }

        employee.setAvailabilityList(availabilityRepository.saveAll(availabilities));
        employeeRepository.save(employee);

        return employee.getAvailabilityList();
    }

    @Transactional
    public boolean saveAll(AvailabilityRequest availabilityRequest) {
        Roster roster = rosterService.findLatestRosterByToDateAndShopOwner(availabilityRequest.getShopOwnerId());
        Employee employee = employeeRepository.findById(availabilityRequest.getEmployeeId()).orElseThrow(() -> new AppException("Cannot find employee!"));
        List<Availability> availabilities = new ArrayList<>();
        Date effectiveDate;
        if (roster != null) {
            effectiveDate = roster.getToDate();
        } else {
            effectiveDate = new Date();
        }

        availabilityRequest
                .getAvailabilityList()
                .forEach(availability ->
                        availabilities.add(new Availability(
                                availability.getId(),
                                availability.getDay(),
                                availability.getStartHour(),
                                availability.getStartMinute(),
                                availability.getEndHour(),
                                availability.getEndMinute(),
                                availability.isAvailable(),
                                employee,
                                effectiveDate)));

        return availabilityRepository.saveAll(availabilities).size() > 0;

    }

}
