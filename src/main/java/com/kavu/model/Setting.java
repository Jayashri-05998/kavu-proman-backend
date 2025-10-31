package com.kavu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_settings")
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean emailAlerts;
    private boolean projectUpdates;
    private boolean weeklySummary;

    private String language;

    private boolean profileVisible;
    private boolean dataSharing;
    private boolean twoFactor;

    private String userEmail; // to associate settings with a user

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isEmailAlerts() { return emailAlerts; }
    public void setEmailAlerts(boolean emailAlerts) { this.emailAlerts = emailAlerts; }

    public boolean isProjectUpdates() { return projectUpdates; }
    public void setProjectUpdates(boolean projectUpdates) { this.projectUpdates = projectUpdates; }

    public boolean isWeeklySummary() { return weeklySummary; }
    public void setWeeklySummary(boolean weeklySummary) { this.weeklySummary = weeklySummary; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public boolean isProfileVisible() { return profileVisible; }
    public void setProfileVisible(boolean profileVisible) { this.profileVisible = profileVisible; }

    public boolean isDataSharing() { return dataSharing; }
    public void setDataSharing(boolean dataSharing) { this.dataSharing = dataSharing; }

    public boolean isTwoFactor() { return twoFactor; }
    public void setTwoFactor(boolean twoFactor) { this.twoFactor = twoFactor; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}

