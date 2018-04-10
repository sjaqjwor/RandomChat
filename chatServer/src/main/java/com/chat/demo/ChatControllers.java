package com.chat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@CrossOrigin
@RestController
public class ChatControllers {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    public void sendMessage(@Payload String message,SimpMessageHeaderAccessor smha){
        System.err.println(smha.getSessionId());
        smha.setSessionId(smha.getSessionId());
        simpMessagingTemplate.convertAndSend("/receive/message",message);
    }
}
