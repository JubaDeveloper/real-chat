package org.jubadeveloper.actors.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/channel/{id}")
    public void messageMapping (String message, @DestinationVariable String id) throws Exception {
        simpMessagingTemplate.convertAndSend("/topic/channel/" + id, message);
    }

}