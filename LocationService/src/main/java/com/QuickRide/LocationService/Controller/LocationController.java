package com.QuickRide.LocationService.Controller;

import com.QuickRide.LocationService.Dto.DriverLocationDto;
import com.QuickRide.LocationService.Dto.NearbyDriversRequestDto;
import com.QuickRide.LocationService.Dto.SaveDriverLocationDto;
import com.QuickRide.LocationService.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping("driver")
    public ResponseEntity<?> drivers(@RequestBody SaveDriverLocationDto request){
        locationService.saveDriverLocation(request.getDriverId(),request.getLatitude(),request.getLongitude());
        return ResponseEntity.status(200).body("Success");
    }

    @PostMapping("drivers")
    public ResponseEntity<?> drivers(@RequestBody NearbyDriversRequestDto nearbyDriversRequestDto){
        java.util.List<DriverLocationDto> list =locationService.findNearbyDrivers(nearbyDriversRequestDto.getLatitude(),nearbyDriversRequestDto.getLongitude(),500);
        return ResponseEntity.status(200).body(list);
    }

}
