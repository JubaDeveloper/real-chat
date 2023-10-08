package org.jubadeveloper.core.usecases.services.models;

import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;

public interface UserServiceModel {
    User getUser (Long id) throws UserNotFoundException;
    User getUserByLogin (String email, String password) throws UserNotFoundException;
    User createUser (User user);
    User updateUser (Long id, User newUser) throws UserNotFoundException;
    void deleteUser (Long id) throws UserNotFoundException;
}
