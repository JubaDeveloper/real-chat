package org.jubadeveloper.core.usecases.services.graphql.models;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface QueryUserServiceModel {
    List<User> queryUsers ();
    Optional<User> queryUserById (Long id);
    List<Channel> channel (User user);
}
