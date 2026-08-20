package com.example.employee_management.ems.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.employee_management.ems.validation.MinimumAge;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank(message = "Employee name is required")
    @Size(max = 100, message = "Employee name must not exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z]+(?:[ '-][A-Za-z]+)*$",
        message = "Employee name can contain only letters, single spaces, hyphens, and apostrophes")
    @Column(nullable = false, length = 100)
    private String employeeName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$",
        message = "Invalid Indian mobile number")
    @Column(nullable = false, unique = true)
    private String mobileNumber;

    @NotBlank(message = "Gender is required")
    @Column(nullable = false)
    private String gender;

    @NotNull(message = "Date of birth is required")
    @PastOrPresent(message = "Date of birth cannot be in the future")
    @MinimumAge(value = 18, message = "Employee must be at least 18 years old")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Status is required")
    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    private String updatedBy;

    private LocalDateTime updatedDate;

    public String getStatus() {
    return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "employee")
    @Builder.Default
    private List<EmployeeProject> projectEmployees = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdBy = "SYSTEM";
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedBy = "SYSTEM";
        updatedDate = LocalDateTime.now();
    }

}
