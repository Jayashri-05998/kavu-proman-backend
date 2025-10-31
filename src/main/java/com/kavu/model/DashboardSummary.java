package com.kavu.model;

import lombok.*;
import java.util.List;


@Builder

public class DashboardSummary {

	
    private int activeProjects;
    public int getActiveProjects() {
		return activeProjects;
	}
    
    private int openTasks;
    private int overdueTasks;

    private List<Activity> recentActivities;
    private List<Deadline> upcomingDeadlines;
	public void setActiveProjects(int activeProjects) {
		this.activeProjects = activeProjects;
	}
	public int getOpenTasks() {
		return openTasks;
	}
	public void setOpenTasks(int openTasks) {
		this.openTasks = openTasks;
	}
	public int getOverdueTasks() {
		return overdueTasks;
	}
	public void setOverdueTasks(int overdueTasks) {
		this.overdueTasks = overdueTasks;
	}
	public List<Activity> getRecentActivities() {
		return recentActivities;
	}
	public void setRecentActivities(List<Activity> recentActivities) {
		this.recentActivities = recentActivities;
	}
	public List<Deadline> getUpcomingDeadlines() {
		return upcomingDeadlines;
	}
	public void setUpcomingDeadlines(List<Deadline> upcomingDeadlines) {
		this.upcomingDeadlines = upcomingDeadlines;
	}
	public DashboardSummary(int activeProjects, int openTasks, int overdueTasks, List<Activity> recentActivities,
			List<Deadline> upcomingDeadlines) {
		super();
		this.activeProjects = activeProjects;
		this.openTasks = openTasks;
		this.overdueTasks = overdueTasks;
		this.recentActivities = recentActivities;
		this.upcomingDeadlines = upcomingDeadlines;
	}
	public DashboardSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
