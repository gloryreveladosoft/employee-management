package com.example.employee_management.ems.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.employee_management.ems.dto.DeptRequest;
import com.example.employee_management.ems.dto.DeptResponse;
import com.example.employee_management.ems.dto.DeptSearchReq;

public interface DepartmentService {

    DeptResponse saveDepartment(DeptRequest deptRequest);

    List<DeptResponse> getAllDepartments();

    DeptResponse getDepartmentById(Long id);

    DeptResponse updateDepartment(Long id, DeptRequest deptRequest);

    void deleteDepartment(Long id);

    Page<DeptResponse> getDepartments(int page, int size);

    List<DeptResponse> getDepartmentsByStatus(String status);

    Page<DeptResponse> searchDepartments(DeptSearchReq request);

    Page<DeptResponse> searchByDepartmentsFields(DeptSearchReq request);

}
