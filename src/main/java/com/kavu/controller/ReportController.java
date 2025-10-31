package com.kavu.controller;

import com.kavu.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin("*")
public class ReportController {

    @Autowired
    private ProjectRepository projectRepository;

    
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new LinkedHashMap<>();

       
        long totalProjects = projectRepository.count();
     
        long totalTasks = 67;
        long completedTasks = 45;
        long overdueTasks = 6;

        stats.put("totalProjects", totalProjects);
        stats.put("totalTasks", totalTasks);
        stats.put("completedTasks", completedTasks);
        stats.put("overdueTasks", overdueTasks);
        return stats;
    }

   
    @GetMapping("/tasks-trend")
    public Map<String, Object> getTaskTrend() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("labels", List.of("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        data.put("completedTasks", List.of(12, 18, 22, 30, 26, 34, 40));
        return data;
    }

    
    @GetMapping("/project-status")
    public Map<String, Object> getProjectStatus() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("labels", List.of("Active", "Completed", "Pending"));
        data.put("values", List.of(6, 5, 3));
        return data;
    }

    
    @GetMapping("/activity")
    public List<Map<String, Object>> getActivity() {
        List<Map<String, Object>> activities = new ArrayList<>();

        activities.add(Map.of("date", "2025-10-10", "type", "project", "title", "New File Sharing Feature", "user", "Joshi", "status", "In Progress"));
        activities.add(Map.of("date", "2025-10-12", "type", "task", "title", "Fix Login Issue", "user", "Maria", "status", "Completed"));
        activities.add(Map.of("date", "2025-10-15", "type", "task", "title", "Testing Chat Integration", "user", "Ankit", "status", "Pending"));
        activities.add(Map.of("date", "2025-10-16", "type", "project", "title", "Dashboard UI Update", "user", "Priya", "status", "Completed"));
        activities.add(Map.of("date", "2025-10-17", "type", "task", "title", "Optimize API", "user", "John", "status", "In Progress"));

        return activities;
    }
}
