package com.chat.demo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReponseRoom {
    private List<Room> rooms;
}
