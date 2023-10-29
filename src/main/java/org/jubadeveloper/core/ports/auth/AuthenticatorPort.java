package org.jubadeveloper.core.ports.auth;

import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.usecases.exceptions.AuthenticationException;

public interface AuthenticatorPort {
    String auth (User user);
    Long checkAuth (String token) throws AuthenticationException;
    String updateToken (String token) throws AuthenticationException;
    String getAuthRequest (String authHeader);
}
