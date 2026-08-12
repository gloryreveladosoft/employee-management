package com.example.employee_management.ems.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeptResponse {

    private Long departmentId;

    private String departmentCode;

    private String departmentName;

    private String status;

    private String createdBy;

    private LocalDateTime createdDate;

    private String updatedBy;

    private LocalDateTime updatedDate;

}
