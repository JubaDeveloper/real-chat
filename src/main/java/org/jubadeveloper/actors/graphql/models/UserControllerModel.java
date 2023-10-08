package org.jubadeveloper.actors.graphql.models;

import org.jubadeveloper.core.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserControllerModel {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User insertUser (User user);
    User updateUser (Long id, User user);
}
