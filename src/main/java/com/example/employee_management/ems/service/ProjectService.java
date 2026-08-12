package com.example.employee_management.ems.service;
import java.util.List;
import com.example.employee_management.ems.entity.Project;

public interface ProjectService {
    Project saveProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(Long id);

}
