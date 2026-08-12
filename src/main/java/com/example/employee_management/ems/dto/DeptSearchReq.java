package com.example.employee_management.ems.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeptSearchReq {
    private String departmentCode;

    private String departmentName;

    private String status;

    private LocalDate fromDate;
    
    private LocalDate toDate;

    private int page = 0;

    private int size = 5;

    private String sortBy = "departmentId";

    private String sortDirection = "asc";
}
