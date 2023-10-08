package org.jubadeveloper.core.usecases.services.graphql;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.ports.repository.ChannelRepository;
import org.jubadeveloper.core.ports.repository.UserRepository;
import org.jubadeveloper.core.usecases.exceptions.ChannelNotFoundException;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;
import org.jubadeveloper.core.usecases.services.graphql.models.MutationChannelServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MutationChannelService implements MutationChannelServiceModel {
    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Channel createChannel(Channel channel) throws UserNotFoundException {
        // First search by user
        User user = userRepository.findById(channel.getUserId()).orElseThrow(() -> new UserNotFoundException(channel.getUserId()));
        Channel channel1 = channelRepository.save(channel);
        Set<Channel> userChannels = user.getChannels();
        userChannels.add(channel1);
        userRepository.save(user);
        return channel1;
    }

    @Override
    public Channel updateChannel(Long id, Channel channel) throws ChannelNotFoundException {
        return channelRepository.findById(id).map((channel1) -> {
            channel1.setName(channel.getName());
            channel1.setDescription(channel.getDescription());
            channel1.setTags(channel.getTags());
            channel1.setPermissions(channel.getPermissions());
            channel1.setUserId(channel.getUserId());
            return channelRepository.save(channel1);
        }).orElseThrow(() -> new ChannelNotFoundException(channel.getId()));
    }
}
