package com.example.employee_management.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.employee_management.ems.entity.Employee;
import com.example.employee_management.ems.exception.DuplicateResourceException;
import com.example.employee_management.ems.exception.ResourceNotFoundException;
import com.example.employee_management.ems.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;

    // @Override
    // public Employee saveEmployee(Employee employee) {
    //     return employeeRepo.save(employee);
    // }

    @Override
    public Employee saveEmployee(Employee employee) {

        if (employeeRepo.existsByEmail(employee.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (employeeRepo.existsByMobileNumber(employee.getMobileNumber())) {
            throw new DuplicateResourceException("Mobile number already exists");
        }

        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setMobileNumber(employee.getMobileNumber());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setStatus(employee.getStatus());
        existingEmployee.setDepartment(employee.getDepartment());

        return employeeRepo.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepo.delete(employee);
    }

    

}
