package com.QuickRide.BookingService.Dto;

import com.QuickRide.EntityService.modules.BookingStatus;
import com.QuickRide.EntityService.modules.Driver;
import com.QuickRide.EntityService.modules.ExactLocation;
import lombok.*;

import java.util.Optional;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingRequestDto {
    Optional<Long> driverId;
    String status;
}
