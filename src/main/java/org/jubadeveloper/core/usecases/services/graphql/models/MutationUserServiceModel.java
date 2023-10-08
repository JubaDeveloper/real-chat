package org.jubadeveloper.core.usecases.services.graphql.models;

import org.jubadeveloper.core.domain.user.User;

public interface MutationUserServiceModel {
    User saveUser (User user);
    User updateUser (Long id, User user);
}
