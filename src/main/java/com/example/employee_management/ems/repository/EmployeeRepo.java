package com.example.employee_management.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee_management.ems.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);
}
