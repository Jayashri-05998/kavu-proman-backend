package com.kavu.controller;

import com.kavu.model.Setting;
import com.kavu.repository.SettingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin("*")
public class SettingController {

    private final SettingRepository settingRepository;

    public SettingController(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    // ✅ Get settings for a specific user by email
    @GetMapping("/{email}")
    public ResponseEntity<Setting> getSettings(@PathVariable String email) {
        Optional<Setting> settings = settingRepository.findByUserEmail(email);
        return settings.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Save or update settings for a user
    @PostMapping("/{email}")
    public ResponseEntity<Setting> saveSettings(@PathVariable String email, @RequestBody Setting setting) {
        // Associate settings with the user email
        setting.setUserEmail(email);

        // If settings already exist for this user, update them
        Optional<Setting> existing = settingRepository.findByUserEmail(email);
        if(existing.isPresent()) {
            Setting existingSetting = existing.get();
            existingSetting.setEmailAlerts(setting.isEmailAlerts());
            existingSetting.setProjectUpdates(setting.isProjectUpdates());
            existingSetting.setWeeklySummary(setting.isWeeklySummary());
            existingSetting.setLanguage(setting.getLanguage());
            existingSetting.setProfileVisible(setting.isProfileVisible());
            existingSetting.setDataSharing(setting.isDataSharing());
            existingSetting.setTwoFactor(setting.isTwoFactor());
            Setting updated = settingRepository.save(existingSetting);
            return ResponseEntity.ok(updated);
        }

        // If not existing, create new
        Setting saved = settingRepository.save(setting);
        return ResponseEntity.ok(saved);
    }
}


