package org.jubadeveloper.actors.rest.model;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.usecases.exceptions.ChannelNotFoundException;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserChannelControllerModel {
    ResponseEntity<String> createChannel (Long userId, Channel channel) throws UserNotFoundException;
    ResponseEntity<String> updateChannel (Long id, Channel newChannel) throws ChannelNotFoundException;
    ResponseEntity<String> deleteChannel (Long id) throws ChannelNotFoundException;
    Set<Channel> listChannelsByUser (Long id) throws UserNotFoundException;
}
