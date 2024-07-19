package com.QuickRide.AuthService.services;

import com.QuickRide.AuthService.Dto.PassengerSignupRequestDto;
import com.QuickRide.AuthService.repositories.PassengerRepository;
import com.QuickRide.EntityService.modules.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService{

    @Autowired
    PassengerRepository authRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    public Passenger signUpPasssenger(PassengerSignupRequestDto passengerSignupRequestDto) {
        Passenger passenger = Passenger.builder()
                .email(passengerSignupRequestDto.getEmail())
                .name(passengerSignupRequestDto.getName())
                .password(bCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword())) // TODO: Encrypt the password
                .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                .build();
        return authRepository.save(passenger);
    }
}
