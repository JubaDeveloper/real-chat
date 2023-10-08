package org.jubadeveloper.core.usecases.services;

import org.jubadeveloper.core.ports.auth.Authenticator;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.usecases.exceptions.AuthenticationException;
import org.jubadeveloper.core.usecases.services.models.AuthServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceModel {
    @Autowired
    Authenticator authenticator;
    @Override
    public String auth(User user) {
        return authenticator.auth(user);
    }

    @Override
    public Long checkAuth(String token) throws AuthenticationException {
        return authenticator.checkAuth(token);
    }

    @Override
    public String updateToken(String token) throws AuthenticationException {
        return authenticator.updateToken(token);
    }

    @Override
    public String getAuthToken(String authToken) {
        return authenticator.getAuthRequest(authToken);
    }
}
