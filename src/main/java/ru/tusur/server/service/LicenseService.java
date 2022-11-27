package ru.tusur.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tusur.server.domain.License;
import ru.tusur.server.repository.LicenseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;

    public License saveLicense(String key) {
        try {
            return saveLicense(UUID.fromString(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public License saveLicense(UUID key) {
        if (licenseRepository.existsByLicenseKey(key)) {
            return null;
        }
        License license = new License();
        license.setLicenseKey(key);
        license.setBlocked(false);
        return licenseRepository.save(license);
    }

    public List<License> getLicenseList() {
        return licenseRepository.findAll();
    }

}
