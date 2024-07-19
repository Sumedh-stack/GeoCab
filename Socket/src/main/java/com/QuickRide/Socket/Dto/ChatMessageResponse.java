package com.QuickRide.Socket.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessageResponse {
    String name;
    long timeStamp;
    String message;
}
