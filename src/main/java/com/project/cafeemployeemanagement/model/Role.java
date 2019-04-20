package com.project.cafeemployeemanagement.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length=60)
    private RoleName name;

    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setRole(this);
    }

    public void removeShift(Employee employee) {
        employees.remove(employee);
        employee.setRole(null);
    }

    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

}
