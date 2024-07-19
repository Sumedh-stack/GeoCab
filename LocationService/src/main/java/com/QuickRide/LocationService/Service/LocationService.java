package com.QuickRide.LocationService.Service;

import com.QuickRide.LocationService.Dto.DriverLocationDto;
import com.QuickRide.LocationService.Dto.SaveDriverLocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private static final String DRIVER_LOCATION_KEY = "driver_locations";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveDriverLocation(String driverId, double latitude, double longitude) {
        GeoOperations<String, String> geoOps = redisTemplate.opsForGeo();
        Point location = new Point(latitude, longitude);
        geoOps.add(DRIVER_LOCATION_KEY, location, driverId);
    }

    public List<DriverLocationDto> findNearbyDrivers(double latitude, double longitude, double radiusDist) {
        GeoOperations<String, String> geoOps = redisTemplate.opsForGeo();
        Distance radius = new Distance(radiusDist, Metrics.KILOMETERS);
        Circle within = new Circle(new Point(latitude, longitude), radius);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = geoOps.radius(DRIVER_LOCATION_KEY, within);
        List<DriverLocationDto> nearbyDrivers = new ArrayList<>();
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult : geoResults) {
            RedisGeoCommands.GeoLocation<String> geoLocation = geoResult.getContent();
            String driverId = geoLocation.getName();
            Point point = geoOps.position(DRIVER_LOCATION_KEY, geoResult.getContent().getName()).get(0);
            Double driverLatitude = point.getX();
            Double driverLongitude = point.getY();
            DriverLocationDto driverLocationDto = new DriverLocationDto();
            driverLocationDto.setDriverId(driverId);
            driverLocationDto.setLatitude(driverLatitude);
            driverLocationDto.setLongitude(driverLongitude);
            nearbyDrivers.add(driverLocationDto);
        }
        return nearbyDrivers;
    }
}
