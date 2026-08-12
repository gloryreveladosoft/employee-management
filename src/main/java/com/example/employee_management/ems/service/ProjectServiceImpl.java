package com.example.employee_management.ems.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.employee_management.ems.entity.Project;
import com.example.employee_management.ems.exception.ResourceNotFoundException;
import com.example.employee_management.ems.repository.ProjectRepo;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Project not found"));
    }

}
