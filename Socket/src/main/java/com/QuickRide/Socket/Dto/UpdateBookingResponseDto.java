package com.QuickRide.Socket.Dto;

import com.QuickRide.EntityService.modules.BookingStatus;
import com.QuickRide.EntityService.modules.Driver;
import lombok.*;

import java.util.Optional;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingResponseDto {
    Long bookingId;
    Optional<Driver> driver;
    BookingStatus status;
}

