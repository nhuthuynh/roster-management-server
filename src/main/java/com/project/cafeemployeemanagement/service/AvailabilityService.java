package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.constant.Constants;
import com.project.cafeemployeemanagement.exception.AppException;
import com.project.cafeemployeemanagement.model.Availability;
import com.project.cafeemployeemanagement.model.Employee;
import com.project.cafeemployeemanagement.payload.AvailabilityRequest;
import com.project.cafeemployeemanagement.payload.AvailabilityResponse;
import com.project.cafeemployeemanagement.repository.AvailabilityRepository;
import com.project.cafeemployeemanagement.repository.EmployeeRepository;
import com.project.cafeemployeemanagement.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public List<AvailabilityResponse> loadAvailabilities(Long employeeId) {
        List<Availability> availabilityList = availabilityRepository.findAllByEmployeeId(employeeId);

        if (availabilityList.size() == 0) {
            return createDefaultAvailability(employeeId);
        }

        return ModelMapper.mapAvailabilitiesToResponse(availabilityList);
    }


    private List<AvailabilityResponse> createDefaultAvailability(Long employeeId) {
        List<Availability> availabilities = new ArrayList<>();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new AppException("Cannot find employee!"));

        for (int eachDayIndex = 0; eachDayIndex < Constants.DEFAULT_AVAILABILITY_DAYS.length; eachDayIndex++) {
            availabilities.add(new Availability(Constants.DEFAULT_AVAILABILITY_DAYS[eachDayIndex], Constants.DEFAULT_START_HOUR, Constants.DEFAULT_START_MINUTE, Constants.DEFAULT_END_HOUR, Constants.DEFAULT_END_MINUTE, true));
        }

        availabilityRepository.saveAll(availabilities);
        employee.setAvailabilityList(availabilities);
        employeeRepository.save(employee);

        return ModelMapper.mapAvailabilitiesToResponse(availabilities);
    }

    @Transactional
    public boolean saveAvailabilities(AvailabilityRequest availabilityRequest){
        List<Availability> availabilities = new ArrayList<>();
        Employee employee = employeeRepository.findById(availabilityRequest.getEmployeeId()).orElseThrow(() -> new AppException("Cannot find employee!"));
        availabilityRequest.getAvailabilityList().forEach(availability -> {
            availabilities.add(new Availability(availability.getId(), availability.getDay(), availability.getStartHour(), availability.getStartMinute(), availability.getEndHour(), availability.getEndMinute(), availability.isAvailable(), employee));
        });

        if(availabilityRepository.saveAll(availabilities).size() > 0) {
            return true;
        }

        return false;
    }

}
