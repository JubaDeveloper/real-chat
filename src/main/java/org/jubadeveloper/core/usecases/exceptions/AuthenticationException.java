package org.jubadeveloper.core.usecases.exceptions;

public class AuthenticationException extends Exception {
    private static final String pattern = "Authentication failed - Invalid token: %s";
    public AuthenticationException(String token) {
        super(String.format(pattern, token));
    }
}
