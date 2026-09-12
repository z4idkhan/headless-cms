package com.project.headless_cms.service;

import com.project.headless_cms.dto.SettingsRequestDTO;
import com.project.headless_cms.dto.SettingsResponseDTO;
import com.project.headless_cms.model.Settings;
import com.project.headless_cms.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public SettingsResponseDTO getSettings() {

        Settings settings = settingsRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> {

                    Settings newSettings = new Settings();

                    newSettings.setSiteName("Headless CMS");
                    newSettings.setSiteUrl("https://headlescms.co");
                    newSettings.setEmailNotifications(true);
                    newSettings.setActivityAlerts(true);
                    newSettings.setTwoFactorAuthentication(false);
                    newSettings.setDarkMode(true);

                    return settingsRepository.save(newSettings);
                });

        return convertToResponseDTO(settings);
    }


    public SettingsResponseDTO updateSettings(
            SettingsRequestDTO request) {

        Settings settings = getSettingsEntity();

        settings.setSiteName(request.getSiteName());
        settings.setSiteUrl(request.getSiteUrl());
        settings.setEmailNotifications(
                request.isEmailNotifications()
        );
        settings.setActivityAlerts(
                request.isActivityAlerts()
        );
        settings.setTwoFactorAuthentication(
                request.isTwoFactorAuthentication()
        );
        settings.setDarkMode(
                request.isDarkMode()
        );

        Settings updatedSettings =
                settingsRepository.save(settings);

        return convertToResponseDTO(updatedSettings);
    }


    private Settings getSettingsEntity() {

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


    private SettingsResponseDTO convertToResponseDTO(
            Settings settings) {

        return new SettingsResponseDTO(
                settings.getId(),
                settings.getSiteName(),
                settings.getSiteUrl(),
                settings.isEmailNotifications(),
                settings.isActivityAlerts(),
                settings.isTwoFactorAuthentication(),
                settings.isDarkMode()
        );
    }
}