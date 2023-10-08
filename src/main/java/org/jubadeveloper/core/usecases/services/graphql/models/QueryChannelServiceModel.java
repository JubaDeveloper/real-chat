package org.jubadeveloper.core.usecases.services.graphql.models;

import org.jubadeveloper.core.domain.channel.Channel;

import java.util.List;

public interface QueryChannelServiceModel {
    Channel getChannelById (Long id);
    List<Channel> getChannels ();
}
