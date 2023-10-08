package org.jubadeveloper.core.usecases.services;

import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.ports.repository.UserRepository;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;
import org.jubadeveloper.core.usecases.services.models.UserServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceModel {
    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser (User user) {
        return this.userRepository.save(user);
    }
    @Override
    public User getUser (Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByLogin(String email, String password) throws UserNotFoundException {
        User user = userRepository.findOneByEmailAndPassword(email, password);
        if (user == null) throw new UserNotFoundException(email, password);
        return user;
    }

    @Override
    public User updateUser (Long id, User newUser) throws UserNotFoundException {
        return userRepository.findById(id).map((user -> {
            user.setEmail(newUser.getEmail());
            user.setName(newUser.getName());
            user.setPassword(newUser.getPassword());
            user.setChannels(newUser.getChannels());
            return userRepository.save(user);
        })).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
