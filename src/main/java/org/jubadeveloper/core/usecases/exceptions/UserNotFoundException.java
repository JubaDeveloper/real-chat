package org.jubadeveloper.core.usecases.exceptions;

public class UserNotFoundException extends Exception {
    private static final String exceptionMessageId = "User with id: %d was not found";
    private static final String exceptionMessageNotFound = "User with email: %s and password: %s was not found";
    public UserNotFoundException (Long id) {
        super(String.format(exceptionMessageId, id));
    }

    public UserNotFoundException (String email, String password) {
        super(String.format(exceptionMessageNotFound, email, password));
    }
}
