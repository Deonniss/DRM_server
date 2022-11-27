package ru.tusur.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tusur.server.domain.License;

import java.util.UUID;

public interface LicenseRepository extends JpaRepository<License, Long> {

    License findByLicenseKey(UUID key);

    boolean existsByLicenseKey(UUID key);

    @Query("select lic.blocked from License lic where lic.licenseKey = :licenseKey")
    boolean isBlocked(@Param("licenseKey") UUID licenseKey);
}
