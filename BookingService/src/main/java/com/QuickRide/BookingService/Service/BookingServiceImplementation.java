package com.QuickRide.BookingService.Service;

import com.QuickRide.BookingService.Dto.*;
import com.QuickRide.BookingService.Repository.BookingRepository;
import com.QuickRide.BookingService.Repository.DriverRepository;
import com.QuickRide.BookingService.Repository.PassengerRepository;
import com.QuickRide.EntityService.modules.Booking;
import com.QuickRide.EntityService.modules.BookingStatus;
import com.QuickRide.EntityService.modules.Driver;
import com.QuickRide.EntityService.modules.Passenger;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImplementation implements BookingService{


    private RestTemplate restTemplate;

    private  PassengerRepository passengerRepository;
    private  BookingRepository bookingRepository;

    @Autowired
    DriverRepository driverRepository;
    @Autowired
    LocationServiceApi locationServiceApi;
    @Autowired
    UberSocketApi uberSocketApi;


    public BookingServiceImplementation(PassengerRepository passengerRepository,
                              BookingRepository bookingRepository
                              ) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
   this.restTemplate=new RestTemplate();
    }
    @Override
    public UpdateBookingResponseDto updateBooking(Long id, UpdateBookingRequestDto requestDto) {
        // Fetch existing booking from repository or database
        Optional<Booking> booking = bookingRepository.findById(id);
        Optional<Driver> driver = null;
        if(requestDto.getDriverId().isPresent()){
            driver = driverRepository.findById(requestDto.getDriverId().get());
        }
        if(booking.isPresent()){
             bookingRepository.updateBookingStatusAndDriverById(id, BookingStatus.SCHEDULED, driver.get());
        }
        return UpdateBookingResponseDto.builder().driver(driver).status(BookingStatus.SCHEDULED).bookingId(id).build();
    }

    @Override
    public Booking createBooking(BookingRequestDto bookingDetails) {

        Optional<Passenger> passenger = passengerRepository.findById(bookingDetails.getPassengerId());
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                .startLocation(bookingDetails.getStartLocation())
                .endLocation(bookingDetails.getEndLocation())
                .passenger(passenger.get())
                .build();
        Booking newBooking = bookingRepository.save(booking);
        System.out.println("Hello");

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://localhost:8082/drivers/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        // Create service instance from Retrofit
//        LocationServiceApi service = retrofit.create(LocationServiceApi.class);

        // Prepare request data

        NearbyDriversRequestDto requestDto = new NearbyDriversRequestDto();
        requestDto.setLatitude(booking.getStartLocation().getLatitude());
        requestDto.setLongitude(booking.getStartLocation().getLongitude());
        processNearbyDrivers(requestDto, bookingDetails.getPassengerId(), booking.getId());


        // Make asynchronous call
//        Call<DriverLocationDto[]> call = service.getNearbyDrivers(requestDto);
//        call.enqueue(new Callback<DriverLocationDto[]>() {
//
//
//            @Override
//            public void onResponse(Call<DriverLocationDto[]> call, Response<DriverLocationDto[]> response) {
//                if (response.isSuccessful()) {
//                    DriverLocationDto[] driverLocations = response.body();
//                    // Process the driver locations
//                    System.out.println("Successfully fetched nearby drivers");
//                    for (DriverLocationDto locationDto : driverLocations) {
//                        System.out.println(locationDto.getDriverId());
//                    }
//                } else {
//                    try {
//                        System.out.println("Failed to fetch nearby drivers: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DriverLocationDto[]> call, Throwable t) {
//                System.out.println("Failed to fetch nearby drivers");
//                t.printStackTrace();
//            }
//        });
//        // Continue with other operations while waiting for the response
        System.out.println("Asynchronous call initiated...");
        return newBooking;
}

    private void processNearbyDrivers(NearbyDriversRequestDto requestDto, Long passengerId, Long bookingId){
        Call<DriverLocationDto[]> call = locationServiceApi.getNearbyDrivers(requestDto);
    call.enqueue(new Callback<DriverLocationDto[]>() {


        @Override
        public void onResponse(Call<DriverLocationDto[]> call, Response<DriverLocationDto[]> response) {
            if (response.isSuccessful()) {
                DriverLocationDto[] driverLocations = response.body();
                // Process the driver locations
                 System.out.println("Successfully fetched nearby drivers");
                List<Long> driverIds = new ArrayList<>();
                for (DriverLocationDto locationDto : driverLocations) {
                    driverIds.add(Long.valueOf(locationDto.getDriverId()));
                }
                RideRequestDto rideRequestDto =
                        RideRequestDto.builder()
                        .driverIds(driverIds)
                        .passengerId(passengerId)
                        .bookingId(bookingId)
                        .build();
                raiseRideRequest(rideRequestDto);

            } else {
                try {
                    System.out.println("Failed to fetch nearby drivers: " + response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<DriverLocationDto[]> call, Throwable t) {
            System.out.println("Failed to fetch nearby drivers");
            t.printStackTrace();
        }
    });
}
    private void raiseRideRequest(RideRequestDto requestDto){
        Call<Boolean> call = uberSocketApi.getNearbyDrivers(requestDto);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean res = response.body();
                    // Process the driver locations
                    System.out.println("Successfully fetched response");
                } else {
                    try {
                        System.out.println("Failed to fetch response : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("Failed to fetch response");
                t.printStackTrace();
            }
        });
    }

}
