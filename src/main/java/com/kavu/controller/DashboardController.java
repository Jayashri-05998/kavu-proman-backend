package com.kavu.controller;

import com.kavu.model.*;

import com.kavu.service.DashboardService;
import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")  // Allow frontend access
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardSummary getDashboard() {
        return dashboardService.getDashboardData();
    }

    @PostMapping("/activities")
   public Activity addActivity(@RequestBody Activity activity) {
    return dashboardService.addActivity(activity);
}

    @PutMapping("/activities/{id}")
public Activity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
    return dashboardService.updateActivity(id, activity);
}

    @DeleteMapping("/activities/{id}")
public String deleteActivity(@PathVariable Long id) {
    dashboardService.deleteActivity(id);
    return "Activity deleted successfully.";
}

// ===== Deadlines =====
@GetMapping("/deadlines")
public List<Deadline> getAllDeadlines() {
    return dashboardService.getAllDeadlines();
}

@PostMapping("/deadlines")
public Deadline addDeadline(@RequestBody Deadline deadline) {
    return dashboardService.addDeadline(deadline);
}

@DeleteMapping("/deadlines/{id}")
public String deleteDeadline(@PathVariable Long id) {
    dashboardService.deleteDeadline(id);
    return "Deadline deleted successfully.";
}
}