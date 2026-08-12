package com.example.employee_management.ems.service;

import java.util.List;

import com.example.employee_management.ems.entity.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

}
