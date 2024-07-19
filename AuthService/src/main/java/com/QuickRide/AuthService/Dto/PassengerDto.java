package com.QuickRide.AuthService.Dto;

import com.QuickRide.EntityService.modules.Passenger;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private String id;

    private String name;

    private String email;

    private String password; // encrypted password

    private String phoneNumber;

    private Date createdAt;

    public static PassengerDto from(Passenger p) {
        return PassengerDto.builder()
                .id(String.valueOf(p.getId()))
                .createdAt(p.getCreatedAt())
                .email(p.getEmail())
                .password(p.getPassword())
                .phoneNumber(p.getPhoneNumber())
                .name(p.getName())
                .build();
    }
}
