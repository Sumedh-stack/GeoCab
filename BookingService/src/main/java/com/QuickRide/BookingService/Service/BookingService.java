package com.QuickRide.BookingService.Service;

import com.QuickRide.BookingService.Dto.BookingRequestDto;
import com.QuickRide.BookingService.Dto.UpdateBookingRequestDto;
import com.QuickRide.BookingService.Dto.UpdateBookingResponseDto;
import com.QuickRide.EntityService.modules.Booking;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    public Booking createBooking(BookingRequestDto bookingRequestDto);
    public UpdateBookingResponseDto updateBooking(Long id, UpdateBookingRequestDto requestDto);
}
