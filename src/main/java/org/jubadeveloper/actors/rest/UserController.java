package org.jubadeveloper.actors.rest;

import org.jubadeveloper.actors.rest.model.UserControllerModel;
import org.jubadeveloper.actors.rest.responses.ResponseWithUser;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.domain.user.UserLevel;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;
import org.jubadeveloper.core.usecases.services.AuthService;
import org.jubadeveloper.core.usecases.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements UserControllerModel {
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Override
    @PostMapping("/user/register")
    public ResponseEntity<ResponseWithUser> register(@RequestBody User user) {
        user.setUserLevel(UserLevel.CLIENT);
        User createdUser = userService.createUser(user);
        String token = authService.auth(createdUser);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseWithUser(createdUser, "User has been created"));
    }

    @Override
    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody User user) throws UserNotFoundException {
        User user1 = userService.getUserByLogin(user.getEmail(), user.getPassword());
        String token = authService.auth(user1);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body("User has been logged in");
    }

    @Override
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUser(id);
    }
}

