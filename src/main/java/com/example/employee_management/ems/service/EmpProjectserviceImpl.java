package com.example.employee_management.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_management.ems.entity.EmployeeProject;
import com.example.employee_management.ems.repository.EmpProjectRepo;

@Service
public class EmpProjectserviceImpl implements EmpProjectService{

    @Autowired
    private EmpProjectRepo employeeProjectRepo;

    @Override
    public EmployeeProject assignProject(EmployeeProject employeeProject) {
        return employeeProjectRepo.save(employeeProject);
    }

}
