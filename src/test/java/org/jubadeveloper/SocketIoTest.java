package org.jubadeveloper;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocketIoTest {
    @Test
    public void connectionTest () throws ExecutionException, InterruptedException {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new StringMessageConverter());
        String url = "ws://127.0.0.1:3000/gs-guide-websocket";
        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter(){
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("Stomp client was been connected.");
            }
        };
        CompletableFuture<StompSession> stompSessionCompletableFuture = stompClient.connectAsync(url, sessionHandler)
                .orTimeout(1500, TimeUnit.MILLISECONDS);
        StompSession stompSession = stompSessionCompletableFuture.get();
        stompSession.subscribe("/topic/greetings", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                System.out.println("New message received: "+ payload);
            }
        });
        stompSession.send("/app/greetings", "Hello stomp server");
        Thread.sleep(5000);
    }
}
