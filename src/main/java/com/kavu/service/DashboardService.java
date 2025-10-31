package com.kavu.service;

import com.kavu.model.*;
import java.util.List;

public interface DashboardService {

    // ===== Dashboard Summary =====
    DashboardSummary getDashboardData();

    // ===== Activities =====
    List<Activity> getAllActivities();
    Activity addActivity(Activity activity);
    Activity updateActivity(Long id, Activity updatedActivity);
    void deleteActivity(Long id);

    // ===== Deadlines =====
    List<Deadline> getAllDeadlines();
    Deadline addDeadline(Deadline deadline);
    void deleteDeadline(Long id);
}
