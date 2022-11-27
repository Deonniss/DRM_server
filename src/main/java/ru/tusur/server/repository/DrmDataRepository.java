package ru.tusur.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tusur.server.domain.DrmData;
import ru.tusur.server.domain.License;

public interface DrmDataRepository extends JpaRepository<DrmData, Long> {

    DrmData findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByHardware(String hardware);

    boolean existsByLicense(License license);
}
