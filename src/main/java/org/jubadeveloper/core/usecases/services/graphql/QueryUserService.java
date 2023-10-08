package org.jubadeveloper.core.usecases.services.graphql;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.ports.repository.ChannelRepository;
import org.jubadeveloper.core.ports.repository.UserRepository;
import org.jubadeveloper.core.usecases.services.graphql.models.QueryUserServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryUserService implements QueryUserServiceModel {
    @Autowired UserRepository userRepository;
    @Autowired
    ChannelRepository channelRepository;
    @Override
    public List<User> queryUsers () {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> queryUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Channel> getChannels (User user) {
        return channelRepository.findAll()
                .stream()
                .filter(channel -> channel.getUserId().equals(user.getId()))
                .toList();
    }
}
