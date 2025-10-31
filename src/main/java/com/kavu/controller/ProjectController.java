package com.kavu.controller;

import com.kavu.model.Project;
import com.kavu.service.EmailService;
import com.kavu.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/projects")
@Slf4j
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    private Logger logger =LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private EmailService emailService; // ✅ Added email service

    // ✅ Create new project & send email
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        Project savedProject = projectService.createProject(project);
        logger.info("Inside createProject Methode");
        

        // Try sending email safely
        try {
        	logger.info("Insode try methode");
            if (savedProject.getAssigneeEmail() != null && !savedProject.getAssigneeEmail().isEmpty()) {
                emailService.sendProjectAssignedEmail(
                        savedProject.getAssigneeEmail(),
                        savedProject.getAssignee(),
                        savedProject.getName(),
                        savedProject.getDeadline() != null
                                ? savedProject.getDeadline().toString()
                                : "No deadline set"
                );
                System.out.println("✅ Email successfully sent to " + savedProject.getAssigneeEmail());
            } else {
                System.out.println("⚠️ No assignee email found. Skipping email sending.");
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
        }

        return savedProject;
    }

    // ✅ Get all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // ✅ Get single project by ID
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // ✅ Update project
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    // ✅ Delete project
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
