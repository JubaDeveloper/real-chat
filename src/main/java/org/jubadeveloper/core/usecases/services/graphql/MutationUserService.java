package org.jubadeveloper.core.usecases.services.graphql;

import org.hibernate.exception.ConstraintViolationException;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.ports.repository.UserRepository;
import org.jubadeveloper.core.usecases.services.graphql.models.MutationUserServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class MutationUserService implements MutationUserServiceModel {
    @Autowired
    UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        try {
            return userRepository.findById(id).map(user1 -> {
                user1.setUserLevel(user.getUserLevel());
                user1.setPassword(user.getPassword());
                user1.setName(user.getName());
                user1.setEmail(user.getEmail());
                return userRepository.save(user1);
            }).orElseGet(() -> null);
        } catch (DataIntegrityViolationException e) {
            String constraintName = ((ConstraintViolationException) e.getCause()).getConstraintName();
            System.out.println(constraintName);
            throw e;
        }
    }
}
