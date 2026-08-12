package com.example.employee_management.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.employee_management.ems.entity.EmployeeProject;
import com.example.employee_management.ems.service.EmpProjectService;

@RestController
@RequestMapping("/emp_project")
public class EmployeeProjectController {

    @Autowired
    private EmpProjectService employeeProjectService;

    @PostMapping
    public EmployeeProject assignProject(
            @RequestBody EmployeeProject employeeProject) {
        return employeeProjectService.assignProject(employeeProject);
    }

}
