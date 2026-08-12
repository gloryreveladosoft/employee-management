package com.example.employee_management.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.employee_management.ems.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>, 
JpaSpecificationExecutor<Department> {
    List<Department> findByStatus(String status);

}
