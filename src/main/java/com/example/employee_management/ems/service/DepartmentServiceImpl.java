package com.example.employee_management.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.employee_management.ems.dto.DeptRequest;
import com.example.employee_management.ems.dto.DeptResponse;
import com.example.employee_management.ems.dto.DeptSearchReq;
import com.example.employee_management.ems.entity.Department;
import com.example.employee_management.ems.exception.ResourceNotFoundException;
import com.example.employee_management.ems.repository.DepartmentRepo;
import com.example.employee_management.ems.specification.DepartmentSpecification;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public DeptResponse saveDepartment(DeptRequest request) {
        Department department = Department.builder()
                .departmentCode(request.getDepartmentCode())
                .departmentName(request.getDepartmentName())
                .status(request.getStatus())
                .build();

        Department savedDepartment = departmentRepo.save(department);
        return mapToResponse(savedDepartment);
    }

    @Override
    public List<DeptResponse> getAllDepartments() {

        return departmentRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DeptResponse getDepartmentById(Long id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Department not found")
                );


        return mapToResponse(department);
    }

    @Override
    public DeptResponse updateDepartment(Long id, DeptRequest request) {
        Department existingDepartment = departmentRepo.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Department not found"));

        existingDepartment.setDepartmentCode(request.getDepartmentCode());
        existingDepartment.setDepartmentName(request.getDepartmentName());
        existingDepartment.setStatus(request.getStatus());
        Department updatedDepartment =
                departmentRepo.save(existingDepartment);

        return mapToResponse(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        departmentRepo.deleteById(id);

    }

    private DeptResponse mapToResponse(Department department) {
        return DeptResponse.builder()
                .departmentId(department.getDepartmentId())
                .departmentCode(department.getDepartmentCode())
                .departmentName(department.getDepartmentName())
                .status(department.getStatus())
                .createdBy(department.getCreatedBy())
                .createdDate(department.getCreatedDate())
                .updatedBy(department.getUpdatedBy())
                .updatedDate(department.getUpdatedDate())
                .build();
    }

    @Override
    public Page<DeptResponse> getDepartments(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);

    return departmentRepo.findAll(pageable)
            .map(department -> DeptResponse.builder()
                    .departmentId(department.getDepartmentId())
                    .departmentCode(department.getDepartmentCode())
                    .departmentName(department.getDepartmentName())
                    .status(department.getStatus())
                    .createdBy(department.getCreatedBy())
                    .createdDate(department.getCreatedDate())
                    .updatedBy(department.getUpdatedBy())
                    .updatedDate(department.getUpdatedDate())
                    .build());
    }

    @Override
    public List<DeptResponse> getDepartmentsByStatus(String status) {

    return departmentRepo.findByStatus(status)
            .stream()
            .map(this::mapToResponse)
            .toList();
    }

    @Override
    public Page<DeptResponse> searchDepartments(DeptSearchReq request) {

    Sort sort = request.getSortDirection().equalsIgnoreCase("desc")
            ? Sort.by(request.getSortBy()).descending()
            : Sort.by(request.getSortBy()).ascending();

    Pageable pageable = PageRequest.of(
            request.getPage(),
            request.getSize(),
            sort
    );

    return departmentRepo.findAll(
                    DepartmentSpecification.search(request),
                    pageable)
            .map(this::mapToResponse);
    }

    @Override
    public Page<DeptResponse> searchByDepartmentsFields(DeptSearchReq request) {
        
    Sort sort = request.getSortDirection().equalsIgnoreCase("desc")
            ? Sort.by(request.getSortBy()).descending()
            : Sort.by(request.getSortBy()).ascending();

    Pageable pageable = PageRequest.of(
            request.getPage(),
            request.getSize(),
            sort);

    return departmentRepo.findAll(
            DepartmentSpecification.search(request),
            pageable)
            .map(this::mapToResponse);
    }

}
