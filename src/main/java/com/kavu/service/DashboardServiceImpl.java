package com.kavu.service;

import com.kavu.model.*;
import com.kavu.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ActivityRepository activityRepo;
    private final DeadlineRepository deadlineRepo;

    public DashboardServiceImpl(ActivityRepository activityRepo, DeadlineRepository deadlineRepo) {
        this.activityRepo = activityRepo;
        this.deadlineRepo = deadlineRepo;
    }

    @Override
    public DashboardSummary getDashboardData() {
        return new DashboardSummary(
                14, // activeProjects
                67, // openTasks
                6,  // overdueTasks
                activityRepo.findAll(),
                deadlineRepo.findAll()
        );
    }

    // ===== Activities =====
    @Override
    public List<Activity> getAllActivities() {
        return activityRepo.findAll();
    }

    @Override
    public Activity addActivity(Activity activity) {
        return activityRepo.save(activity);
    }

    @Override
    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepo.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedActivity.getTitle());
                    existing.setDescription(updatedActivity.getDescription());
                    existing.setDate(updatedActivity.getDate());
                    return activityRepo.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepo.deleteById(id);
    }

    // ===== Deadlines =====
    @Override
    public List<Deadline> getAllDeadlines() {
        return deadlineRepo.findAll();
    }

    @Override
    public Deadline addDeadline(Deadline deadline) {
        return deadlineRepo.save(deadline);
    }

    @Override
    public void deleteDeadline(Long id) {
        deadlineRepo.deleteById(id);
    }
}
