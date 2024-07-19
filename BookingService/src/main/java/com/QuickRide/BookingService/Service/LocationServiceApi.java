package com.QuickRide.BookingService.Service;

import com.QuickRide.BookingService.Dto.DriverLocationDto;
import com.QuickRide.BookingService.Dto.NearbyDriversRequestDto;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

@Component
public interface LocationServiceApi {

    @POST("/drivers")
    Call<DriverLocationDto[]> getNearbyDrivers(@Body NearbyDriversRequestDto requestDto);

}
