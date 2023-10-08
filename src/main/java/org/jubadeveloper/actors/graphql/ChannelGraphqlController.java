package org.jubadeveloper.actors.graphql;

import org.jubadeveloper.actors.graphql.models.ChannelControllerModel;
import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.usecases.exceptions.ChannelNotFoundException;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;
import org.jubadeveloper.core.usecases.services.graphql.MutationChannelService;
import org.jubadeveloper.core.usecases.services.graphql.QueryChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class ChannelGraphqlController implements ChannelControllerModel {
    @Autowired
    QueryChannelService queryChannelService;
    @Autowired
    MutationChannelService mutationChannelService;
    @Override
    @QueryMapping
    public Channel getChannel(@Argument Long id) {
        return queryChannelService.getChannelById(id);
    }

    @Override
    @QueryMapping
    public List<Channel> listChannels() {
        return queryChannelService.getChannels();
    }

    @Override
    @MutationMapping
    public Channel updateChannel(@Argument Long id, @Argument Channel newChannel) throws ChannelNotFoundException {
        return mutationChannelService.updateChannel(id, newChannel);
    }

    @Override
    @MutationMapping
    public Channel insertChannel(@Argument Channel channel) throws UserNotFoundException {
        return mutationChannelService.createChannel(channel);
    }
}
