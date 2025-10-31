package com.kavu.controller;

import com.kavu.model.ProjectTask;
import com.kavu.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/project-tasks")
@CrossOrigin(origins = "*")
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping
    public ProjectTask createTask(@RequestBody ProjectTask task) {
        return projectTaskService.addTask(task);
    }

    @GetMapping("/project/{projectId}")
    public List<ProjectTask> getTasksByProject(@PathVariable Long projectId) {
        return projectTaskService.getTasksByProject(projectId);
    }

    @PutMapping("/{id}")
    public ProjectTask updateTask(@PathVariable Long id, @RequestBody ProjectTask task) {
        return projectTaskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        projectTaskService.deleteTask(id);
    }
}
