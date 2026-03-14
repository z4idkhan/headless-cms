package com.project.headless_cms.controller;

import com.project.headless_cms.model.Settings;
import com.project.headless_cms.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping
    public Settings getSettings() {
        return settingsService.getSettings();
    }

    @PutMapping
    public Settings updateSettings(@RequestBody Settings settings) {
        return settingsService.updateSettings(settings);
    }
}