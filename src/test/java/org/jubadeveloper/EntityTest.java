package org.jubadeveloper;

import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.ports.repository.ChannelRepository;
import org.jubadeveloper.core.ports.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChannelRepository channelRepository;

    @Order(1)
    @Test
    public void shouldCreateUser () {
        User user = new User("juba", "juba@gmail.com", "juba");
        userRepository.save(user);
        assertThat(user.getEmail()).isEqualTo("juba@gmail.com");
    }
    @Order(2)
    @Test
    public void shouldCreateUserChannel () {
        User user = userRepository.findOneByEmailAndPassword("juba@gmail.com", "juba");
        Channel channel = new Channel("My channel", List.of("Test"), "Channel created for test", null);
        user.setChannels(Set.of(channel));
        User updatedUser = userRepository.save(user);
        assertThat(updatedUser.getChannels()).hasSize(1);
        List<Channel> channels = channelRepository.findAll();
        List<User> users = userRepository.findAll();
        assertThat(channels).hasSize(1);
        assertThat(users).hasSize(1);
    }
}
