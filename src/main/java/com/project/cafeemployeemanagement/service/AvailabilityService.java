package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.exception.AppException;
import com.project.cafeemployeemanagement.model.Availability;
import com.project.cafeemployeemanagement.model.Employee;
import com.project.cafeemployeemanagement.payload.AvailabilityRequest;
import com.project.cafeemployeemanagement.payload.AvailabilityResponse;
import com.project.cafeemployeemanagement.payload.LatestRosterDatesPayLoad;
import com.project.cafeemployeemanagement.repository.AvailabilityRepository;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.util.ModelMapper;
import com.project.cafeemployeemanagement.util.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
        } else {
            LocalDate latestDate = getLatestEffectiveDateInAvailabilities(availabilities);
            availabilities = availabilities.stream()
                    .filter(availability ->
                            availability.getEffectiveDate().isEqual(latestDate) ||
                                    availability.getEffectiveDate().isAfter(latestDate)).distinct().limit(7).collect(Collectors.toList());
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
        LatestRosterDatesPayLoad latestRosterDates = rosterService.findLatestRosterByToDateAndShopOwner(availabilityRequest.getShopOwnerId());
        List<Availability> availabilities = availabilityRepository.findByEmployeeId(availabilityRequest.getEmployeeId());
        LocalDate latestDate = getLatestEffectiveDateInAvailabilities(availabilities);
        List<Availability> updatingAvaiList = new ArrayList<>();

        if (latestRosterDates != null && availabilities.size() > 7) { // if roster is made and if there is 2 version then get the second version update with roster toDate
            List<Availability> latestAvailabilities = availabilities.stream().filter(availability -> availability.getEffectiveDate().equals(latestDate)).collect(Collectors.toList());
            availabilityRequest
                    .getAvailabilityList().forEach(avaiRequest -> {
                Availability newAvai = latestAvailabilities.stream()
                        .filter(a -> a.getId().equals(avaiRequest.getId()))
                        .findFirst().orElse(new Availability());
                updatingAvaiList.add(setupAvailability(employee, avaiRequest, newAvai));
            });
        } else if (latestRosterDates != null && availabilities.size() == 7) { // if roster is made and if there is only one version then add second version with roster toDate
            availabilityRequest
                    .getAvailabilityList()
                    .forEach(avaiRequest -> updatingAvaiList.add(setupAvailability(employee, avaiRequest, new Availability())));
        } else { // if roster is not made and if there is 1 version then update only one version
            availabilityRequest
                    .getAvailabilityList().forEach(avaiRequest -> {
                        Availability existedAvai = availabilities.stream()
                                .filter(a -> a.getId().equals(avaiRequest.getId()))
                                .findFirst().orElse(new Availability());
                updatingAvaiList.add(setupAvailability(employee, avaiRequest, existedAvai));
            });
        }

        return availabilityRepository.saveAll(updatingAvaiList).size() > 0;
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
