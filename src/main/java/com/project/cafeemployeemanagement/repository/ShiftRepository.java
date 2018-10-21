package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
