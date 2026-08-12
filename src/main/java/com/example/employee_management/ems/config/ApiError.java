package com.example.employee_management.ems.config;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    private LocalDateTime timestamp;
    private String error;
    private int status;
    private String message;
    private String path;
}
