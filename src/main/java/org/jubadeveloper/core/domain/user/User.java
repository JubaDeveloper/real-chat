package org.jubadeveloper.core.domain.user;

import jakarta.persistence.*;
import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.device_information.DeviceInformation;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User_")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(length = 256, unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;
    @OneToMany
    private List<DeviceInformation> devicesInformation;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            targetEntity = Channel.class
    )
    @JoinColumn(name = "userId")
    private Set<Channel> channels;

    public User() {
        this.userLevel = UserLevel.CLIENT;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userLevel = UserLevel.CLIENT;
    }

    public List<DeviceInformation> getDevicesInformation() {
        return devicesInformation;
    }

    public void setDevicesInformation(List<DeviceInformation> devicesInformation) {
        this.devicesInformation = devicesInformation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }
}
