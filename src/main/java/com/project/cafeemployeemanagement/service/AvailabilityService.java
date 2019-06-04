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
import com.project.cafeemployeemanagement.util.utils;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RosterService rosterService;

    private static final Logger logger = LoggerFactory.getLogger(Availability.class);

    @Transactional
    public List<Availability> findByEmployeeId(final Long employeeId) {
        List<Availability> availabilities = availabilityRepository.findByEmployeeId(employeeId);

        if (availabilities.size() == 0) {
            availabilities = createDefault(employeeId);
        }

        return availabilities;
    }

    /*
    *  Find employee's available days by id and effective date
    *
    *  if employee updated availabilities after a roster is made
    *  there will be second version of his or her availabilities
    *  select this ones
    *  if employee have not updated, there will be one version
    *  select this ones
    * */

    @Transactional
    public List<Availability> findByEffectiveDateAfterAndEmployeeId(final LocalDate rosterToDate, final Long employeeId) {
        List<Availability> availabilities = findByEmployeeId(employeeId);
        LocalDate latestEffDate = getLatestEffectiveDateInAvailabilities(availabilities);

        availabilities = availabilities.stream().filter(availability ->
                availability.getEffectiveDate().isEqual(latestEffDate) &&
                (availability.getEffectiveDate().equals(rosterToDate)
                        || availability.getEffectiveDate().isBefore(rosterToDate)))
                .collect(Collectors.toList());

        return availabilities;
    }

    public List<AvailabilityResponse> findResponsesByEffectiveDateAfterAndEmployeeId( final LocalDate rosterToDate, final Long employeeId) {
        return ModelMapper.mapAvailabilitiesToResponse(findByEffectiveDateAfterAndEmployeeId(rosterToDate, employeeId));
    }

    public List<AvailabilityResponse> findResponsesByEmployeeId(final Long employeeId) {
        return ModelMapper.mapAvailabilitiesToResponse(findByEmployeeId(employeeId));
    }


    private List<Availability> createDefault(Long employeeId) {
        List<Availability> availabilities = new ArrayList<>();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new AppException("Cannot find employee!"));
        logger.info(Calendar.getInstance().getTime().toString());
        for (int eachDayIndex = 0; eachDayIndex < Constants.DEFAULT_AVAILABILITY_DAYS.length; eachDayIndex++) {
            availabilities.add(new Availability(
                    Constants.DEFAULT_AVAILABILITY_DAYS[eachDayIndex],
                    Constants.DEFAULT_START_HOUR,
                    Constants.DEFAULT_START_MINUTE,
                    Constants.DEFAULT_END_HOUR,
                    Constants.DEFAULT_END_MINUTE,
                    true,
                    utils.getToday()));
        }

        employee.setAvailabilityList(availabilityRepository.saveAll(availabilities));
        employeeRepository.save(employee);

        return employee.getAvailabilityList();
    }

    @Transactional
    public boolean saveAll(AvailabilityRequest availabilityRequest) {
        Employee employee = employeeRepository.findById(availabilityRequest.getEmployeeId()).orElseThrow(() -> new AppException("Cannot find employee!"));
        List<Availability> availabilities = availabilityRepository.findByEmployeeId(availabilityRequest.getEmployeeId());

        // online 1 version of availabilities
        if (availabilities.size() == 7) {
            availabilityRequest
                    .getAvailabilityList().stream().map(avaiRequest -> setupAvailability(employee, avaiRequest, new Availability()))
                    .forEach((newAvai) -> availabilities.add(newAvai));
        } else {
            LocalDate latestDate = getLatestEffectiveDateInAvailabilities(availabilities);
            List<Availability> newAvailabilities = availabilities.stream().filter(availability -> availability.getEffectiveDate().equals(latestDate)).collect(Collectors.toList());
            availabilityRequest
                    .getAvailabilityList().forEach(avaiRequest -> {
                Availability newAvai = newAvailabilities.stream()
                        .filter(a -> a.getId().equals(avaiRequest.getId()))
                        .findFirst().orElseThrow(() -> new AppException("cannot find item " + avaiRequest.getDay()));
                availabilities.add(setupAvailability(employee, avaiRequest, newAvai));
            });
        }

        return availabilityRepository.saveAll(availabilities).size() > 0;
    }

    private LocalDate getLatestEffectiveDateInAvailabilities(final List<Availability> availabilities) {
        return availabilities.stream().map(Availability::getEffectiveDate).max(LocalDate::compareTo).orElse(utils.getToday());
    }

    private Availability setupAvailability(final Employee employee, final AvailabilityResponse response, final Availability newAvai) {
        Availability availability = newAvai;
        availability.setEmployee(employee);
        availability.setEffectiveDate(utils.parseLocalDate(response.getEffectiveDate()));
        availability.setDay(response.getDay());
        availability.setAvailable(response.isAvailable());
        availability.setEndHour(response.getEndHour());
        availability.setEndMinute(response.getEndMinute());
        availability.setStartHour(response.getStartHour());
        availability.setStartMinute(response.getStartMinute());

        return availability;
    }

}
