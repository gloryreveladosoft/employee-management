package com.example.employee_management.ems.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeptRequest {
    @NotBlank(message = "Department code is required")
    @Size(max = 10, message = "Department code must not exceed 10 characters")
    private String departmentCode;


    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name must not exceed 100 characters")
    private String departmentName;


    @NotBlank(message = "Status is required")
    private String status;

}
