package com.QuickRide.AuthService.Dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    private String email;
    private String password;
}
