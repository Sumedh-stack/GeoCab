package com.QuickRide.BookingService.Service;

import com.QuickRide.BookingService.Dto.DriverLocationDto;
import com.QuickRide.BookingService.Dto.NearbyDriversRequestDto;
import com.QuickRide.BookingService.Dto.RideRequestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UberSocketApi {
    @POST("/newride")
    Call<Boolean> getNearbyDrivers(@Body RideRequestDto requestDto);

}
