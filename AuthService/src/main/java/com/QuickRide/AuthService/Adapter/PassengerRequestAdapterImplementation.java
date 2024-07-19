package com.QuickRide.AuthService.Adapter;

import com.QuickRide.AuthService.Dto.PassengerDto;
import com.QuickRide.EntityService.modules.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PassengerRequestAdapterImplementation implements PassengerRequestAdapter{


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Passenger convertDto(PassengerDto request) {
        return Passenger.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword())).build();
    }
}
