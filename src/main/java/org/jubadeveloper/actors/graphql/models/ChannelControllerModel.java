package org.jubadeveloper.actors.graphql.models;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.usecases.exceptions.ChannelNotFoundException;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;

import java.util.List;

public interface ChannelControllerModel {
    Channel getChannel (Long id);
    List<Channel> listChannels ();
    Channel updateChannel (Long id, Channel newChannel) throws ChannelNotFoundException;
    Channel insertChannel (Channel channel) throws UserNotFoundException;
}
