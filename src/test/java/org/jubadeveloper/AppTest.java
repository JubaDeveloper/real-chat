package org.jubadeveloper;

import org.json.JSONException;
import org.json.JSONObject;
import org.jubadeveloper.actors.rest.responses.ResponseWithUser;
import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.ports.repository.ChannelRepository;
import org.jubadeveloper.core.ports.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.MultiValueMapAdapter;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChannelRepository channelRepository;
    String serverUrl;
    public AppTest (@Value("${local.server.port}") int port) {
        serverUrl = "http://localhost:" + port;
    }
    @Order(1)
    @Test
    public void shouldCreatedUser () {
        User user = new User("Juba", "juba@gmail.com", "juba");
        ResponseEntity<ResponseWithUser> result = testRestTemplate.postForEntity(serverUrl + "/user/register", user, ResponseWithUser.class);
        assertThat(Objects.requireNonNull(result.getBody()).response).isEqualTo("User has been created");
    }
    @Order(2)
    @Test
    public void shouldCreateChannel () {
        assertThat(userRepository.findAll()).hasSize(1);
        User user = new User("Juba", "juba@gmail.com", "juba");
        ResponseEntity<String> result = testRestTemplate.postForEntity(serverUrl + "/user/login", user, String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        // Create channel
        Channel channel = new Channel(
                "My channel",
                List.of("tag1"),
                "Channel for testing",
                null);

        Map<String, List<String>> headerMap = new HashMap<>();
        headerMap.put("Authorization", result.getHeaders().get("Authorization"));

        MultiValueMapAdapter<String, String> multiValueMapAdapter = new MultiValueMapAdapter<>(headerMap);
        HttpEntity<Channel> httpEntity = new HttpEntity<>(channel, multiValueMapAdapter);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(serverUrl + "/panel/channel", httpEntity, String.class);
        assertThat(responseEntity.getBody()).isEqualTo(String.format("Channel %s has been created", channel.getName()));
    }
    @Order(3)
    @Test
    public void checkCreatedChannel () throws JSONException {
        User user = new User("Juba", "juba@gmail.com", "juba");
        ResponseEntity<String> result = testRestTemplate.postForEntity(serverUrl + "/user/login", user, String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        //
        Map<String, List<String>> headerMap = new HashMap<>();
        headerMap.put("Authorization", result.getHeaders().get("Authorization"));

        MultiValueMapAdapter<String, String> multiValueMapAdapter = new MultiValueMapAdapter<>(headerMap);
        HttpEntity<List> httpEntity = new HttpEntity<>(multiValueMapAdapter);
        ResponseEntity<List> responseEntity = testRestTemplate.exchange(serverUrl + "/panel/channels", HttpMethod.GET, httpEntity, List.class);
        assertThat(responseEntity.getBody()).hasSize(1);
    }
}
