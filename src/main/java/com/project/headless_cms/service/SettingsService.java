package com.project.headless_cms.service;

import com.project.headless_cms.model.Settings;
import com.project.headless_cms.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public Settings getSettings() {
        return settingsRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Settings settings = new Settings();
                    settings.setSiteName("Headless CMS");
                    settings.setSiteUrl("https://headlescms.co");
                    settings.setEmailNotifications(true);
                    settings.setActivityAlerts(true);
                    settings.setTwoFactorAuthentication(false);
                    settings.setDarkMode(true);
                    return settingsRepository.save(settings);
                });
    }

    public Settings updateSettings(Settings request) {
        Settings settings = getSettings();

        settings.setSiteName(request.getSiteName());
        settings.setSiteUrl(request.getSiteUrl());
        settings.setEmailNotifications(request.isEmailNotifications());
        settings.setActivityAlerts(request.isActivityAlerts());
        settings.setTwoFactorAuthentication(request.isTwoFactorAuthentication());
        settings.setDarkMode(request.isDarkMode());

        return settingsRepository.save(settings);
    }
}