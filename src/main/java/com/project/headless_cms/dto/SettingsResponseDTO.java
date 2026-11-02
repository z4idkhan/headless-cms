package com.project.headless_cms.dto;

public class SettingsResponseDTO {

    private Long id;

    private String siteName;

    private String siteUrl;

    private boolean emailNotifications;

    private boolean activityAlerts;

    private boolean twoFactorAuthentication;

    private boolean darkMode;

    public SettingsResponseDTO(
            Long id,
            String siteName,
            String siteUrl,
            boolean emailNotifications,
            boolean activityAlerts,
            boolean twoFactorAuthentication,
            boolean darkMode
    ) {
        this.id = id;
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.emailNotifications = emailNotifications;
        this.activityAlerts = activityAlerts;
        this.twoFactorAuthentication = twoFactorAuthentication;
        this.darkMode = darkMode;
    }

    public Long getId() {
        return id;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public boolean isActivityAlerts() {
        return activityAlerts;
    }

    public boolean isTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }

    public boolean isDarkMode() {
        return darkMode;
    }
}