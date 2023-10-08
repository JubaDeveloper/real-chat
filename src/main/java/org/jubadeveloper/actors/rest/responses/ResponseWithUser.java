package org.jubadeveloper.actors.rest.responses;

import org.jubadeveloper.core.domain.user.User;

public class ResponseWithUser {
    public User user;
    public String response;

    public ResponseWithUser(User user, String response) {
        this.user = user;
        this.response = response;
    }
}
