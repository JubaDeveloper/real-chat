package org.jubadeveloper.actors.rest.advices;

import org.jubadeveloper.core.usecases.exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class InvalidAuthTokenAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public String interceptInvalidToken (AuthenticationException authenticationException) {
        return authenticationException.getMessage();
    }
}
