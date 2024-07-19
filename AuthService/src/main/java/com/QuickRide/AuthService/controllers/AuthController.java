package com.QuickRide.AuthService.controllers;


import com.QuickRide.AuthService.Dto.AuthRequestDto;
import com.QuickRide.AuthService.Dto.AuthResponseDto;
import com.QuickRide.AuthService.Dto.PassengerSignupRequestDto;
import com.QuickRide.AuthService.services.JwtService;
import com.QuickRide.AuthService.services.AuthService;
import com.QuickRide.EntityService.modules.Passenger;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    @Autowired
    AuthService authService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto) {
        Passenger response = authService.signUpPasssenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signUp(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        if(authentication.isAuthenticated()) {
            String jwtToken = jwtService.createToken(authRequestDto.getEmail());

            ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(12000000)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return new ResponseEntity<>(AuthResponseDto.builder().success(true).build(), HttpStatus.OK);
        }
       return null;
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Inside validate controller");
        for(Cookie cookie: request.getCookies()) {
            System.out.println(cookie.getName() + " " + cookie.getValue());
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

