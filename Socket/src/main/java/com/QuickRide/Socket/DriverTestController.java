package com.QuickRide.Socket;

import com.QuickRide.EntityService.modules.Driver;
import com.QuickRide.Socket.Dto.RideRequestDto;
import com.QuickRide.Socket.Dto.RideResponseDto;
import com.QuickRide.Socket.Dto.UpdateBookingRequestDto;
import com.QuickRide.Socket.Dto.UpdateBookingResponseDto;
import com.QuickRide.Socket.Producers.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("")
public class DriverTestController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

   private KafkaProducerService kafkaProducerService;
    private final RestTemplate restTemplate;

    public DriverTestController(KafkaProducerService kafkaProducerService){
        restTemplate = new RestTemplate();
        this.kafkaProducerService=kafkaProducerService;
    }


    @GetMapping("/kafka")
    public Boolean help(){
        kafkaProducerService.publishMessage("sample-topic","Hello");
        return true;
    }
    @PostMapping("/newride")
    public ResponseEntity<?> newRide(@RequestBody RideRequestDto requestDto){
       return ResponseEntity.status(200).body(sendDriverRideMessage(requestDto));
    }

    public Boolean sendDriverRideMessage(RideRequestDto rideRequestDto) {
        try{
            messagingTemplate.convertAndSend("/topic/receiveRide", rideRequestDto);
            return true;
        } catch (Error e){
            return false;
        }
    }

//    @MessageMapping("/rideResponse/{userId}")
//    public void riderResponseHandler(RideResponseDto rideResponseDto, @DestinationVariable Long userId){
//
//        UpdateBookingRequestDto updateBookingRequestDto = UpdateBookingRequestDto.builder()
//                .driverId(Optional.ofNullable(userId)).build();
//        ResponseEntity<UpdateBookingResponseDto> updateBookingResponseDto = this.restTemplate.postForEntity("http://localhost:8001/booking/"+rideResponseDto.bookingId, updateBookingRequestDto, UpdateBookingResponseDto.class);
//        System.out.println("Driver " + userId);
//        System.out.println(rideResponseDto.getResponse());
//    }


    @MessageMapping("/rideResponse/{userId}")
    public synchronized void rideResponseHandler(@DestinationVariable String userId, RideResponseDto rideResponseDto) {

        System.out.println(rideResponseDto.getResponse() + " " + userId);
        UpdateBookingRequestDto requestDto = UpdateBookingRequestDto.builder()
                .driverId(Optional.of(Long.parseLong(userId)))
                .status("SCHEDULED")
                .build();
        try {
            ResponseEntity<UpdateBookingResponseDto> result = this.restTemplate.postForEntity(
                    "http://localhost:8001/booking/" + rideResponseDto.bookingId,
                    requestDto,
                    UpdateBookingResponseDto.class
            );
            System.out.println(result.getStatusCode());
            // Process result if needed
        } catch (RestClientException e) {
            System.err.println("Error while calling the booking update API: " + e.getMessage());
            // Handle or log the exception appropriately


//        System.out.println(result.getStatusCode());
        }

    }}