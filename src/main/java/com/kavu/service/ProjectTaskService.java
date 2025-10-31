package com.kavu.service;

import com.kavu.model.ProjectTask;
import com.kavu.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addTask(ProjectTask task) {
        return projectTaskRepository.save(task);
    }

    public List<ProjectTask> getTasksByProject(Long projectId) {
        return projectTaskRepository.findByProjectId(projectId);
    }

    public ProjectTask updateTask(Long id, ProjectTask updatedTask) {
        ProjectTask existing = projectTaskRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTaskName(updatedTask.getTaskName());
            existing.setAssignedTo(updatedTask.getAssignedTo());
            existing.setStatus(updatedTask.getStatus());
            existing.setAssignedDate(updatedTask.getAssignedDate());
            existing.setDueDate(updatedTask.getDueDate());
            return projectTaskRepository.save(existing);
        }
        return null;
    }

    public ProjectTaskRepository getProjectTaskRepository() {
		return projectTaskRepository;
	}

	public void setProjectTaskRepository(ProjectTaskRepository projectTaskRepository) {
		this.projectTaskRepository = projectTaskRepository;
	}

	public void deleteTask(Long id) {
        projectTaskRepository.deleteById(id);
    }
}
