package com.QuickRide.BookingService.Dto;

import com.QuickRide.EntityService.modules.Booking;
import com.QuickRide.EntityService.modules.BookingStatus;
import com.QuickRide.EntityService.modules.ExactLocation;
import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Builder
@Data
public class BookingRequestDto {

    Long passengerId;
    ExactLocation startLocation;
    ExactLocation endLocation;

    @Override
    public String toString() {
        return "BookingRequestDto{" +
                "passengerId=" + passengerId +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation ;
    }
}
