package com.QuickRide.BookingService.Repository;

import com.QuickRide.EntityService.modules.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
