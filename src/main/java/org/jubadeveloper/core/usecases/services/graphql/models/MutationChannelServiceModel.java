package org.jubadeveloper.core.usecases.services.graphql.models;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.usecases.exceptions.ChannelNotFoundException;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;

public interface MutationChannelServiceModel {
    Channel createChannel (Channel channel) throws UserNotFoundException;
    Channel updateChannel (Long id, Channel channel) throws ChannelNotFoundException;
}
