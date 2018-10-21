package com.project.cafeemployeemanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_type")
public class EmployeeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private int hourlyWorkPermit;

    @OneToMany(mappedBy = "employeeType",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setEmployeeType(this);
    }

    public void removeShift(Employee employee) {
        employees.remove(employee);
        employee.setEmployeeType(null);
    }

    public EmployeeType() {}

    public EmployeeType(EmployeeTypeValues values) {
        this.type = values.getTitle();
        this.hourlyWorkPermit = values.getLimitedWorkHours();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHourlyWorkPermit() {
        return hourlyWorkPermit;
    }

    public void setHourlyWorkPermit(int hourlyWorkPermit) {
        this.hourlyWorkPermit = hourlyWorkPermit;
    }
}
