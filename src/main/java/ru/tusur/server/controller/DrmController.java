package ru.tusur.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tusur.server.util.DrmHandler;

@RestController
public class DrmController {

    @GetMapping("/login")
    public ResponseEntity<?> login(String username, String password) {
        return ResponseEntity.ok(DrmHandler.isCorrectPassword(username, password));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(String username, String password) {
        return ResponseEntity.ok(DrmHandler.addUser(username, password));
    }

    @PostMapping("/add/hardware")
    public ResponseEntity<?> addHardware(String username, String hardware) {
        return ResponseEntity.ok(DrmHandler.addHardware(username, hardware));
    }

    @GetMapping("/check/hardware")
    public ResponseEntity<?> checkHardware(String username, String hardware) {
        return ResponseEntity.ok(DrmHandler.checkHardware(username, hardware));
    }

    @PostMapping("/add/license")
    public ResponseEntity<?> addLicense(String username, String key) {
        return ResponseEntity.ok(DrmHandler.addLicense(username, key));
    }

    @GetMapping("/cherck/license")
    public ResponseEntity<?> checkLicense(String username, String key) {
        return ResponseEntity.ok(DrmHandler.checkLicense(username, key));
    }
}
