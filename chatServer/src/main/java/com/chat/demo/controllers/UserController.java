package com.chat.demo.controllers;

import com.chat.demo.controllers.requests.Room;
import org.springframework.web.bind.annotation.*;
import com.chat.demo.controllers.reponses.ReponseRoom;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    private List<Room> rooms = new ArrayList<Room>();

    @GetMapping("/")
    public ReponseRoom getRoom(){
        return ReponseRoom.builder().rooms(rooms).build();
    }
    @PostMapping("/made")
    public ReponseRoom  madeRoom(@RequestBody Room name){
        rooms.add(name);
        Collections.reverse(rooms);
        return ReponseRoom.builder().rooms(rooms).build();
    }
}
