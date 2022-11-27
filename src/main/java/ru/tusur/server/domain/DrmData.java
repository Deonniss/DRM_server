package ru.tusur.server.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "drm_data")
@Data
public class DrmData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "hardware")
    private String hardware;

    @OneToOne
    @JoinColumn(name = "license_key")
    private License license;
}
