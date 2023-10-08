package org.jubadeveloper.core.usecases.services.models;

import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.usecases.exceptions.AuthenticationException;

public interface AuthServiceModel {
    String auth (User user);
    Long checkAuth (String token) throws AuthenticationException;
    String updateToken (String token) throws AuthenticationException;
    String getAuthToken (String authToken);
}
