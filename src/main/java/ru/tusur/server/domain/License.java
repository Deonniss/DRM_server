package ru.tusur.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "license")
@Data
public class License {

    @Id
    @Column(name = "license_key")
    private UUID licenseKey;

    @Column(name = "blocked")
    private boolean blocked;
}
