package com.example.employee_management.ems.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_management.ems.dto.DeptRequest;
import com.example.employee_management.ems.dto.DeptResponse;
import com.example.employee_management.ems.dto.DeptSearchReq;
import com.example.employee_management.ems.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // @PostMapping
    // public DeptResponse saveDepartment(@RequestBody DeptRequest deptRequest){
    //     return departmentService.saveDepartment(deptRequest);
    // }

    @PostMapping
    public ResponseEntity<DeptResponse> saveDepartment(
        @RequestBody @Valid DeptRequest request) {
    DeptResponse response = departmentService.saveDepartment(request);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("/all")
    public List<DeptResponse> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DeptResponse getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    public DeptResponse updateDepartmentById(@PathVariable Long id,
         @RequestBody DeptRequest deptRequest){
        return departmentService.updateDepartment(id, deptRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public Page<DeptResponse> getDepartments(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {

    return departmentService.getDepartments(page, size);
    }

    @GetMapping("/filter")
    public List<DeptResponse> getDepartmentsByStatus(
        @RequestParam String status) {
    return departmentService.getDepartmentsByStatus(status);
    }

    @PostMapping("filter")
    public Page<DeptResponse> searchDepartments(
        @RequestBody DeptSearchReq request) {

    return departmentService.searchDepartments(request);
    }

    // @GetMapping
    // public Page<DeptResponse> getDepartmentsByFilter(
    //     @RequestParam(required = false) String departmentCode,
    //     @RequestParam(required = false) String departmentName,
    //     @RequestParam(required = false) String status,
    //     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //     LocalDate fromDate,
    //     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //     LocalDate toDate,
    //     @RequestParam(defaultValue = "0") int page,
    //     @RequestParam(defaultValue = "5") int size,
    //     @RequestParam(defaultValue = "departmentId") String sortBy,
    //     @RequestParam(defaultValue = "asc") String sortDirection) {

    // DeptSearchReq request = new DeptSearchReq();

    // request.setDepartmentCode(departmentCode);
    // request.setDepartmentName(departmentName);
    // request.setStatus(status);
    // request.setFromDate(fromDate);
    // request.setToDate(toDate);
    // request.setPage(page);
    // request.setSize(size);
    // request.setSortBy(sortBy);
    // request.setSortDirection(sortDirection);

    // return departmentService.searchDepartments(request);
    // }


    @GetMapping
    public Page<DeptResponse> getDepartmentsByFilter(
            @RequestParam(required = false) String departmentCode,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fromDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "departmentId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        if (page < 0) {
            throw new IllegalArgumentException("Page number must be greater than or equal to 0");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }

        if (fromDate != null && toDate != null && fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("From date cannot be greater than To date");
        }

        DeptSearchReq request = new DeptSearchReq();
        request.setDepartmentCode(departmentCode);
        request.setDepartmentName(departmentName);
        request.setStatus(status);
        request.setFromDate(fromDate);
        request.setToDate(toDate);
        request.setPage(page);
        request.setSize(size);
        request.setSortBy(sortBy);
        request.setSortDirection(sortDirection);

        return departmentService.searchDepartments(request);
   }
}
