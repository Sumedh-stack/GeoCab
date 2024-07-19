package com.QuickRide.LocationService.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveDriverLocationDto {
    String driverId;
    Double latitude;
    Double longitude;
}
