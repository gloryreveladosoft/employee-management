package com.example.employee_management.ems.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.employee_management.ems.dto.DeptSearchReq;
import com.example.employee_management.ems.entity.Department;

import jakarta.persistence.criteria.Predicate;

public class DepartmentSpecification {
    public static Specification<Department> search(DeptSearchReq request) {

        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (request.getDepartmentCode() != null &&
                    !request.getDepartmentCode().isBlank()) {

                predicate = cb.and(predicate,
                        cb.like(
                                cb.lower(root.get("departmentCode")),
                                "%" + request.getDepartmentCode().toLowerCase() + "%"
                        ));
            }

            if (request.getDepartmentName() != null &&
                    !request.getDepartmentName().isBlank()) {

                predicate = cb.and(predicate,
                        cb.like(
                                cb.lower(root.get("departmentName")),
                                "%" + request.getDepartmentName().toLowerCase() + "%"
                        ));
            }

            if (request.getStatus() != null &&
                    !request.getStatus().isBlank()) {

                predicate = cb.and(predicate,
                        cb.equal(
                                cb.lower(root.get("status")),
                                request.getStatus().toLowerCase()
                        ));
            }

            if (request.getFromDate() != null) {

                predicate = cb.and(predicate,
                        cb.greaterThanOrEqualTo(
                                root.get("createdDate"),
                                request.getFromDate().atStartOfDay()
                        ));
            }

            if (request.getToDate() != null) {

                predicate = cb.and(predicate,
                        cb.lessThanOrEqualTo(
                                root.get("createdDate"),
                                request.getToDate().atTime(23, 59, 59)
                        ));
            }
            return predicate;
        };
    }

}
