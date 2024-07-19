package com.QuickRide.Socket.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessageRequest {
    String name;
    String message;
}
