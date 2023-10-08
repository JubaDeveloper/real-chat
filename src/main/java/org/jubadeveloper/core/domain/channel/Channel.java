package org.jubadeveloper.core.domain.channel;

import jakarta.persistence.*;
import org.jubadeveloper.core.domain.channel.childs.Permission;
import org.jubadeveloper.core.domain.user.User;

import java.util.List;
import java.util.Set;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @ElementCollection
    private List<String> tags;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Permission permissions;
    @Column(updatable = false, insertable = false)
    private Long userId;
    @ManyToMany(
            fetch = FetchType.LAZY,
            targetEntity = User.class
    )
    @JoinColumn(name = "id")
    Set<User> participants;

    public Channel() {}

    public Channel(String name, List<String> tags, String description, Permission permission) {
        this.name = name;
        this.tags = tags;
        this.description = description;
        this.permissions = permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPermissions(Permission permissions) {
        this.permissions = permissions;
    }

    public Permission getPermissions() {
        return permissions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
