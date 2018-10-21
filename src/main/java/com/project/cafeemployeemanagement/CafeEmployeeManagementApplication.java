package com.project.cafeemployeemanagement;

import com.project.cafeemployeemanagement.model.*;
import com.project.cafeemployeemanagement.repository.*;
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
    EmployeeRepository employeeRepository;

    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    EmployeeTypeRepository employeeTypeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmployeeShiftRepository employeeShiftRepository;

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

	public static void main(String[] args) {
		SpringApplication.run(CafeEmployeeManagementApplication.class, args);
	}

	@Override
	public void run(String... args) {
        if (employeeRepository.findAll().size() == 0) {

            EmployeeType fullTime = new EmployeeType(EmployeeTypeValues.FULL_TIME);
            EmployeeType partTime = new EmployeeType(EmployeeTypeValues.PART_TIME);
            EmployeeType partTimeStudent = new EmployeeType(EmployeeTypeValues.PART_TIME_STUDENT);

            employeeTypeRepository.save(fullTime);
            employeeTypeRepository.save(partTime);
            employeeTypeRepository.save(partTimeStudent);

            Role admin = new Role(RoleName.ROLE_ADMIN.toString());
            Role employee = new Role(RoleName.ROLE_EMPLOYEE.toString());
            Role manager = new Role(RoleName.ROLE_MANAGER.toString());

            roleRepository.save(admin);
            roleRepository.save(employee);
            roleRepository.save(manager);

            Employee emp1 = new Employee("Stephen", "King", 1234, "Stephen.King@gmail.com", fullTime, manager, 25);
            emp1.setAccount(new Account("45343534"));
            Employee emp2 = new Employee("J.R.R.", "Tolkien", 1234567890, "J.R.R..Tolkien@gmail.com", fullTime, employee, 30);
            emp1.setAccount(new Account("12312"));
            Employee emp3 = new Employee("Stephen", "Chou", 1234567890, "Stephen.Chou@gmail.com", fullTime, admin, 35);
            emp1.setAccount(new Account("12312"));
            Employee emp4 = new Employee("Robert", "Downey Junior", 1234567890, "Robert.Junior@gmail.com", fullTime, employee, 27);
            emp1.setAccount(new Account("234234"));
            Employee emp5 = new Employee("Chris", "Hemsworth", 1234567890, "Chris.Hamsworth@gmail.com", partTimeStudent, employee, 25);
            emp1.setAccount(new Account("234234"));
            Employee emp6 = new Employee("Chris", "Evan", 0466555000, "Chris.Evan@gmail.com", partTimeStudent, employee, 30);
            emp1.setAccount(new Account("123432"));
            Employee emp7 = new Employee("Chris", "Pratt", 0466555111, "Chris.Pratt@gmail.com", partTimeStudent, employee, 30);
            emp1.setAccount(new Account("2343424"));

            employeeRepository.save(emp1);
            employeeRepository.save(emp2);
            employeeRepository.save(emp3);
            employeeRepository.save(emp4);
            employeeRepository.save(emp5);
            employeeRepository.save(emp6);
            employeeRepository.save(emp7);
        }
	}
}
