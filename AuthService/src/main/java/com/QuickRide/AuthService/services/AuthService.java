package com.QuickRide.AuthService.services;

import com.QuickRide.AuthService.Dto.PassengerSignupRequestDto;
import com.QuickRide.EntityService.modules.Passenger;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    Passenger signUpPasssenger(PassengerSignupRequestDto passenger);
}
