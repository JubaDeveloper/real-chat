package org.jubadeveloper.actors.rest.model;

import org.jubadeveloper.actors.rest.responses.ResponseWithUser;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface UserControllerModel {
    ResponseEntity<ResponseWithUser> register (User user);
    ResponseEntity<String> login (User user) throws UserNotFoundException;
    User getUser (Long id) throws UserNotFoundException;
}
