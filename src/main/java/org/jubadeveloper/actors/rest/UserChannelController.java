package org.jubadeveloper.actors.rest;

import org.jubadeveloper.actors.rest.model.UserChannelControllerModel;
import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.channel.childs.Permission;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.usecases.exceptions.ChannelNotFoundException;
import org.jubadeveloper.core.usecases.exceptions.UserNotFoundException;
import org.jubadeveloper.core.usecases.services.ChannelService;
import org.jubadeveloper.core.usecases.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserChannelController implements UserChannelControllerModel {
    @Autowired
    ChannelService channelService;
    @Autowired
    UserService userService;

    @Override
    @PostMapping("/panel/channel")
    public ResponseEntity<String> createChannel(@RequestAttribute(value = "userId") Long userId,
                                                @RequestBody Channel channel) throws UserNotFoundException {
        if (channel.getPermissions() == null) {
            channel.setPermissions(new Permission(true, true));
        }
        User user = userService.getUser(userId);
        Set<Channel> channels = user.getChannels();
        channel.setUserId(user.getId());
        channels.add(channel);
        user.setChannels(channels);
        userService.updateUser(userId, user);
        return ResponseEntity
                .ok()
                .body(String.format("Channel %s has been created", channel.getName()));
    }

    @Override
    @PutMapping("/panel/channel/{id}")
    public ResponseEntity<String> updateChannel(@PathVariable Long id, @RequestBody Channel newChannel) throws ChannelNotFoundException {
        channelService.update(id, newChannel);
        ResponseEntity
                .ok()
                .body(String.format("Channel %d has been updated", id));
        return null;
    }

    @Override
    @DeleteMapping("/panel/channel/{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable Long id) throws ChannelNotFoundException {
        channelService.deleteChannel(id);
        return ResponseEntity
                .ok()
                .body(String.format("Channel %d has been deleted", id));
    }

    @Override
    @GetMapping("/panel/channels")
    public Set<Channel> listChannelsByUser(@RequestAttribute(name = "userId") Long userId) throws UserNotFoundException {
        return userService.getUser(userId).getChannels();
    }
}
