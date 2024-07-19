package com.QuickRide.AuthService.Adapter;

import com.QuickRide.AuthService.Dto.PassengerDto;
import com.QuickRide.EntityService.modules.Passenger;
import org.springframework.stereotype.Component;

@Component
public interface PassengerRequestAdapter {
    Passenger convertDto(PassengerDto request);
}
