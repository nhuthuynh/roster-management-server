package com.project.cafeemployeemanagement;

import com.project.cafeemployeemanagement.service.EmployeeTypeService;
import com.project.cafeemployeemanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        CafeEmployeeManagementApplication.class,
		Jsr310JpaConverters.class
})
public class CafeEmployeeManagementApplication implements CommandLineRunner{

	@Autowired
	private RoleService roleService;

	@Autowired
	private EmployeeTypeService employeeTypeService;

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

	public static void main(String[] args) {
		SpringApplication.run(CafeEmployeeManagementApplication.class, args);
	}

	@Override
	public void run(String... args) {
		roleService.initRoles();
		employeeTypeService.initEmployeeTypes();
	}
}
