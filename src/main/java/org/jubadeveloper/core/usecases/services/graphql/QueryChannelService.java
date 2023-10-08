package org.jubadeveloper.core.usecases.services.graphql;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.ports.repository.ChannelRepository;
import org.jubadeveloper.core.usecases.services.graphql.models.QueryChannelServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QueryChannelService implements QueryChannelServiceModel {
    @Autowired
    ChannelRepository channelRepository;
    @Override
    public Channel getChannelById(Long id) {
        return channelRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public List<Channel> getChannels() {
        return channelRepository.findAll();
    }
}
