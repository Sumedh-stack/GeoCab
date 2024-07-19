package com.QuickRide.Socket.Dto;


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
