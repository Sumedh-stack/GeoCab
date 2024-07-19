package com.project.UberReviewService.adapter;

import com.project.UberReviewService.DTO.CreateRequestDto;
import com.project.UberReviewService.Repositories.BookingRepository;
import com.project.UberReviewService.modules.Booking;
import com.project.UberReviewService.modules.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestReviewAdapterImplementation implements RequestReviewAdapter {
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Review convertDto(CreateRequestDto requestReview) {
        Long bookingID = requestReview.getBookingId();
        Optional<Booking> booking = bookingRepository.findById(bookingID);
        return booking.map(value -> Review.builder().Rating(requestReview.getRating()).content(requestReview.getContent()).booking(value).build()).orElse(null);
    }
}
