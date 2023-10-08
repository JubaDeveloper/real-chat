package org.jubadeveloper.core.domain.device_information;

import jakarta.persistence.*;
import org.jubadeveloper.core.domain.user.User;

import java.time.LocalDate;

@Entity
public class DeviceInformation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "User_.id", nullable = false, updatable = false)
    private User user;
    @Column(nullable = true)
    private String name;
    private String ip;
    @Column(nullable = true)
    private String browserName;
    private LocalDate createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }
}
