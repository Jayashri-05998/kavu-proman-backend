package com.kavu.service;

import com.kavu.model.Project;
import com.kavu.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmailService emailService;

    public Project createProject(Project project) {
        Project savedProject = projectRepository.save(project);

        // ✅ Send email after saving project
        if (savedProject.getAssigneeEmail() != null && !savedProject.getAssigneeEmail().isEmpty()) {
            emailService.sendProjectAssignedEmail(
                    savedProject.getAssigneeEmail(),
                    savedProject.getAssignee(),
                    savedProject.getName(),
                    savedProject.getDeadline() != null ? savedProject.getDeadline().toString() : "No deadline set"
            );
        }

        return savedProject;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project updateProject(Long id, Project project) {
        return projectRepository.findById(id)
                .map(existing -> {
                    existing.setName(project.getName());
                    existing.setDescription(project.getDescription());
                    existing.setDeadline(project.getDeadline());
                    existing.setStatus(project.getStatus());
                    existing.setAssignee(project.getAssignee());
                    existing.setAssigneeEmail(project.getAssigneeEmail());
                    return projectRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
