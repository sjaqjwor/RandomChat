package com.chat.demo;

import org.springframework.web.bind.annotation.*;
import com.chat.demo.ReponseRoom;
import java.util.ArrayList;
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
        rooms.forEach(s->System.err.println(s));
        return ReponseRoom.builder().rooms(rooms).build();
    }
}
