package com.kavu.service;

import com.kavu.model.Task;
import com.kavu.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> getAll() {
        return repo.findAll();
    }

    public List<Task> getByStatus(String status) {
        if (status.equalsIgnoreCase("all")) return repo.findAll();
        return repo.findByStatus(status);
    }

    public Task add(Task task) {
        return repo.save(task);
    }

    public Task update(Task task) {
        return repo.save(task);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
