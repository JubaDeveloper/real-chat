package org.jubadeveloper.core.usecases.services.models;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.usecases.exceptions.ChannelNotFoundException;

import java.util.List;
import java.util.Set;

public interface ChannelServiceModel {
    Channel create (Channel channel);
    List<Channel> listChannels ();
    Channel get (Long id) throws ChannelNotFoundException;
    Channel update (Long id, Channel channel) throws ChannelNotFoundException;
    void deleteChannel (Long id) throws ChannelNotFoundException;
}
