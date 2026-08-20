package com.example.employee_management.ems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employee_management.ems.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);

    @Query("""
    SELECT e
    FROM Employee e
    WHERE
        LOWER(e.employeeName) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%'))
        OR e.mobileNumber LIKE CONCAT('%', :search, '%')
        OR LOWER(e.department.departmentName) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    Page<Employee> searchEmployees(@Param("search") String search,
            Pageable pageable);

    @Query("""
        SELECT e
        FROM Employee e
        WHERE
            (
                LOWER(e.employeeName) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%'))
                OR e.mobileNumber LIKE CONCAT('%', :search, '%')
                OR LOWER(e.department.departmentName) LIKE LOWER(CONCAT('%', :search, '%'))
            )
            AND LOWER(e.status) = LOWER(:status)
        """)
    Page<Employee> searchEmployeesByStatus(
            @Param("search") String search,
            @Param("status") String status,
            Pageable pageable);

}
