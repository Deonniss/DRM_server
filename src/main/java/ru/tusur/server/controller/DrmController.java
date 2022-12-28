package ru.tusur.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tusur.server.service.DrmService;
import ru.tusur.server.service.LicenseService;

@RestController
@RequiredArgsConstructor
public class DrmController {

    private final DrmService drmService;
    private final LicenseService licenseService;

    @GetMapping("/login")
    public ResponseEntity<?> login(String username, String password, String hardware) {
        return ResponseEntity.ok(drmService.login(username, password, hardware));
    }

    @GetMapping("/registration")
    public ResponseEntity<?> registration(String username, String password, String hardware, String license) {
        return ResponseEntity.ok(drmService.registration(username, password, hardware, license));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(drmService.getUsers());
    }

    @PostMapping("/saveLicense")
    public ResponseEntity<?> saveLicense(String key) {
        return ResponseEntity.ok(licenseService.saveLicense(key));
    }

    @GetMapping("/licenseList")
    public ResponseEntity<?> getLicenseList() {
        return ResponseEntity.ok(licenseService.getLicenseList());
    }
}
