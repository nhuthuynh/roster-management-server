package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.model.Role;
import com.project.cafeemployeemanagement.model.RoleName;
import com.project.cafeemployeemanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void initRoles() {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role(RoleName.ROLE_ADMIN));
            roleRepository.save(new Role(RoleName.ROLE_EMPLOYEE));
            roleRepository.save(new Role(RoleName.ROLE_MANAGER));
        }
    }
}
