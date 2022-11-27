package ru.tusur.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tusur.server.domain.DrmData;
import ru.tusur.server.repository.DrmDataRepository;
import ru.tusur.server.repository.LicenseRepository;

import java.util.List;
import java.util.UUID;

import static ru.tusur.server.domain.StatusCode.*;

@Service
@RequiredArgsConstructor
public class DrmService {

    private final DrmDataRepository repository;
    private final LicenseRepository licenseRepository;

    public List<DrmData> getUsers() {
        return repository.findAll();
    }

    public int login(String username, String password, String hardware) {

        DrmData drmData = repository.findByUsername(username);

        if (drmData == null) {
            return LOGIN_FAILED_201.getCode();
        } else if (!drmData.getPassword().equals(password)) {
            return LOGIN_FAILED_202.getCode();
        } else if (!drmData.getHardware().equals(hardware)) {
            return HARDWARE_FAILED_230.getCode();
        } else if (drmData.getLicense().isBlocked()){
            return LICENSE_FAILED_221.getCode();
        } else {
            return LOGIN_SUCCESS_101.getCode();
        }
    }

    public int registration(String username, String password, String hardware, String license) {

        if (repository.existsByUsername(username)) {
            return REGISTRATION_FAILED_210.getCode();
        } else if (repository.existsByHardware(hardware)) {
            return HARDWARE_FAILED_231.getCode();
        } else if (repository.existsByLicense(licenseRepository.findByLicenseKey(UUID.fromString(license)))) {
            return LICENSE_FAILED_220.getCode();
        } else if (!licenseRepository.existsByLicenseKey(UUID.fromString(license))){
            return LICENSE_FAILED_221.getCode();
        } else if (licenseRepository.isBlocked(UUID.fromString(license))){
            return LICENSE_FAILED_222.getCode();
        } else {
            DrmData drmData = new DrmData();
            drmData.setUsername(username);
            drmData.setPassword(password);
            drmData.setHardware(hardware);
            drmData.setLicense(licenseRepository.findByLicenseKey(UUID.fromString(license)));
            repository.save(drmData);
            return REGISTRATION_SUCCESS_110.getCode();
        }
    }
}
