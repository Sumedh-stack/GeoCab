package com.project.UberReviewService.Repositories;

import com.project.UberReviewService.modules.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}
