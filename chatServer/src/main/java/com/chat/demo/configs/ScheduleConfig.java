package com.chat.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConfig {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(cron = "*/30 * * * * ?")
    public void cron(){
        simpMessagingTemplate.convertAndSend("/messageBroker/room/alert/1",10);
    }
}
