package com.chat.demo.controllers.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {
    String roomname;
}
