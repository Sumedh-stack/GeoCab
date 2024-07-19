package com.QuickRide.Socket;

import com.QuickRide.Socket.Dto.ChatMessageRequest;
import com.QuickRide.Socket.Dto.ChatMessageResponse;
import com.QuickRide.Socket.Dto.TestRequest;
import com.QuickRide.Socket.Dto.TestResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    TestResponse send(TestRequest testRequest){
        System.out.println("DNJC");
        return TestResponse.builder().data(testRequest.data).build();
    }


    @MessageMapping("/message")
    @SendTo("/topic/message")
    ChatMessageResponse sendMessage(ChatMessageRequest request){
        return  ChatMessageResponse.builder()
                .message(request.getMessage())
                .timeStamp(System.currentTimeMillis())
                .name(request.getName())
                .build();
    }
}
