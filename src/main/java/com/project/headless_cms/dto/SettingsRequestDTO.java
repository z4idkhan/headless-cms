package com.project.headless_cms.dto;

public class SettingsRequestDTO {

    private String siteName;

    private String siteUrl;

    private boolean emailNotifications;

    private boolean activityAlerts;

    private boolean twoFactorAuthentication;

    private boolean darkMode;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public boolean isActivityAlerts() {
        return activityAlerts;
    }

    public void setActivityAlerts(boolean activityAlerts) {
        this.activityAlerts = activityAlerts;
    }

    public boolean isTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }

    public void setTwoFactorAuthentication(boolean twoFactorAuthentication) {
        this.twoFactorAuthentication = twoFactorAuthentication;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }
}