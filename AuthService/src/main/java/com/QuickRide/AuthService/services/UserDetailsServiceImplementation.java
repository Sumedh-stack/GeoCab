package com.QuickRide.AuthService.services;

import com.QuickRide.AuthService.helpers.AuthPassengerDetails;
import com.QuickRide.AuthService.repositories.PassengerRepository;
import com.QuickRide.EntityService.modules.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    public PassengerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) {
       Passenger passenger = repository.findByEmail(email);
        return new AuthPassengerDetails(passenger);
    }
}