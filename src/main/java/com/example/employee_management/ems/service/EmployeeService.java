package com.example.employee_management.ems.service;

import org.springframework.data.domain.Page;
import com.example.employee_management.ems.entity.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    Page<Employee> getEmployees(
            String search, String status,
            int page, int size,
            String sortBy, String sortDirection);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

}
