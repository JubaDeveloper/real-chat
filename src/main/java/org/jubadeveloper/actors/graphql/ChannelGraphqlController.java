package org.jubadeveloper.actors.graphql;

import org.jubadeveloper.actors.graphql.models.ChannelControllerModel;
import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.usecases.services.graphql.MutationChannelService;
import org.jubadeveloper.core.usecases.services.graphql.QueryChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

public class ChannelGraphqlController implements ChannelControllerModel {
    @Autowired
    QueryChannelService queryChannelService;
    @Autowired
    MutationChannelService mutationChannelService;
    @Override
    @QueryMapping
    public Channel getChannel(@Argument Long id) {
        return null;
    }

    @Override
    @QueryMapping
    public List<Channel> listChannels() {
        return null;
    }

    @Override
    @MutationMapping
    public Channel updateChannel(@Argument Long id, @Argument Channel newChannel) {
        return null;
    }

    @Override
    @MutationMapping
    public Channel createChannel(@Argument Channel channel) {
        return null;
    }
}
