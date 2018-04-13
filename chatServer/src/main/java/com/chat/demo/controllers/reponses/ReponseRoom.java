package com.chat.demo.controllers.reponses;

import com.chat.demo.controllers.requests.Room;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReponseRoom {
    private List<Room> rooms;
}
