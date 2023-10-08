package org.jubadeveloper.actors.graphql;

import org.jubadeveloper.actors.graphql.models.UserControllerModel;
import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.usecases.services.graphql.MutationUserService;
import org.jubadeveloper.core.usecases.services.graphql.QueryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserGraphqlController implements UserControllerModel {
    @Autowired
    QueryUserService queryUserService;
    @Autowired
    MutationUserService mutationUserService;
    @Override
    @QueryMapping
    public List<User> getAllUsers() {
        return queryUserService.queryUsers();
    }
    @Override
    @QueryMapping
    public Optional<User> getUserById(@Argument Long id) {
        return queryUserService.queryUserById(id);
    }
    @Override
    @MutationMapping
    public User insertUser(@Argument User user) {
        return mutationUserService.saveUser(user);
    }
    @Override
    @MutationMapping
    public User updateUser(@Argument Long id, @Argument User newUser) {
        return mutationUserService.updateUser(id, newUser);
    }

    @Override
    public List<Channel> channels (User user) {
        return queryUserService.getChannels(user);
    }
}
