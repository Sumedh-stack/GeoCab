package com.QuickRide.BookingService.Controller;


import com.QuickRide.BookingService.Dto.BookingRequestDto;
import com.QuickRide.BookingService.Dto.UpdateBookingRequestDto;
import com.QuickRide.BookingService.Dto.UpdateBookingResponseDto;
import com.QuickRide.BookingService.Service.BookingService;
import com.QuickRide.BookingService.Service.BookingServiceImplementation;
import com.QuickRide.EntityService.modules.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class BookingController {

    private BookingService bookingService;

    BookingController(BookingServiceImplementation bookingServiceImplementation){
        this.bookingService=bookingServiceImplementation;
    }

    @PostMapping("/booking")
    ResponseEntity<?> createBooking(@RequestBody BookingRequestDto bookingRequestDto){
        System.out.println(bookingRequestDto.toString());
        Booking booking = bookingService.createBooking(bookingRequestDto);
        return ResponseEntity.status(200).body(booking);
    }

    @PostMapping("/booking/{id}")
    ResponseEntity<UpdateBookingResponseDto> updateBooking(@PathVariable Long id, @RequestBody UpdateBookingRequestDto bookingRequestDto){
        UpdateBookingResponseDto updateBookingResponseDto= bookingService.updateBooking(id,bookingRequestDto);
        return new ResponseEntity<>(bookingService.updateBooking(id, bookingRequestDto),HttpStatus.OK);
    }

}
