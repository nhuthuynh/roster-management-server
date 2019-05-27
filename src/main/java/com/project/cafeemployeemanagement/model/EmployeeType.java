package com.project.cafeemployeemanagement.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_type")
public class EmployeeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length=60)
    private EmployeeTypeValues type;

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
        this.type = values;
        this.hourlyWorkPermit = values.equals(EmployeeTypeValues.PART_TIME_STUDENT) ? 20 : 0;
    }

    public EmployeeTypeValues getType() {
        return type;
    }

    public void setType(EmployeeTypeValues type) {
        this.type = type;
    }

    public int getHourlyWorkPermit() {
        return hourlyWorkPermit;
    }

    public void setHourlyWorkPermit(int hourlyWorkPermit) {
        this.hourlyWorkPermit = hourlyWorkPermit;
    }
}
