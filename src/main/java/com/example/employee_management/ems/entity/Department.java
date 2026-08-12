package com.example.employee_management.ems.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="department")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;
    @NotBlank(message = "Department code is required")
    @Size(max = 10, message = "Department code must not exceed 10 characters")
    @Column(name = "department_code", nullable = false, unique = true, length = 20)
    private String departmentCode;

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name must not exceed 100 characters")
    @Column(name = "department_name", nullable = false, unique = true, length = 100)
    private String departmentName;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        this.createdBy = "SYSTEM";
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedBy = "SYSTEM";
        this.updatedDate = LocalDateTime.now();
    }
}

