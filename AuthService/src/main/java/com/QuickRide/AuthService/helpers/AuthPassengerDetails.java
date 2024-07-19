package com.QuickRide.AuthService.helpers;

import com.QuickRide.EntityService.modules.Passenger;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class AuthPassengerDetails extends Passenger implements UserDetails {


    private String username;
    private String password;

     public AuthPassengerDetails(Passenger passenger){
         this.username=passenger.getEmail();
         this.password=passenger.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
