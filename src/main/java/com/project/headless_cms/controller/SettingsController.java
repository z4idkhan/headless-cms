package com.project.headless_cms.controller;

import com.project.headless_cms.dto.SettingsRequestDTO;
import com.project.headless_cms.dto.SettingsResponseDTO;
import com.project.headless_cms.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping
    public SettingsResponseDTO getSettings() {
        return settingsService.getSettings();
    }

    @PutMapping
    public SettingsResponseDTO updateSettings(
            @RequestBody SettingsRequestDTO request) {

        return settingsService.updateSettings(request);
    }
}