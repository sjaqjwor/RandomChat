package com.chat.demo.controllers;

import com.chat.demo.controllers.requests.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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

    @MessageMapping("/{chatId}")
    public void sendDefaultMessage(@DestinationVariable(value = "chatId") String chatid,@Payload  Message message){
        simpMessagingTemplate.convertAndSend("/messageBroker/room/"+chatid,message);
    }

    @MessageMapping("/{chatId}/message")
    public void sendMessage(@DestinationVariable(value = "chatId") String chatid, @Payload Message message){
        simpMessagingTemplate.convertAndSend("/messageBroker/room/"+chatid,message);
    }
}
