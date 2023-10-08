package org.jubadeveloper.core.usecases.services;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.ports.repository.ChannelRepository;
import org.jubadeveloper.core.usecases.exceptions.ChannelNotFoundException;
import org.jubadeveloper.core.usecases.services.models.ChannelServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChannelService implements ChannelServiceModel {
    @Autowired
    ChannelRepository channelRepository;
    @Override
    public Channel create(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public List<Channel> listChannels() {
        return channelRepository.findAll();
    }

    @Override
    public Channel get(Long id) throws ChannelNotFoundException {
        return channelRepository.findById(id).orElseThrow(() -> new ChannelNotFoundException(id));
    }

    @Override
    public Channel update(Long id, Channel channel) throws ChannelNotFoundException {
        return channelRepository.findById(id).map(channel1 -> {
            channel1.setDescription(channel.getDescription());
            channel1.setName(channel.getName());
            channel1.setTags(channel.getTags());
            channel1.setUserId(channel.getUserId());
            return channelRepository.save(channel1);
        }).orElseThrow(() -> new ChannelNotFoundException(id));
    }

    @Override
    public void deleteChannel(Long id) throws ChannelNotFoundException {
        channelRepository.findById(id).orElseThrow(() -> new ChannelNotFoundException(id));
        channelRepository.deleteById(id);
    }
}
