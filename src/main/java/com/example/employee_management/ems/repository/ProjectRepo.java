package com.example.employee_management.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.employee_management.ems.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long>{

}
