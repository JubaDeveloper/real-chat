package org.jubadeveloper.actors.graphql.models;

import org.jubadeveloper.core.domain.channel.Channel;

import java.util.List;

public interface ChannelControllerModel {
    Channel getChannel (Long id);
    List<Channel> listChannels ();
    Channel updateChannel (Long id, Channel newChannel);
    Channel createChannel (Channel channel);
}
