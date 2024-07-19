package com.QuickRide.Socket.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RideResponseDto {

public Boolean response;

public Long bookingId;
}
