package com.QuickRide.Socket.Consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "sample-topic")
    public void Listener(String message){
       System.out.println("kafka message from topic is  " + message);
    }

}
