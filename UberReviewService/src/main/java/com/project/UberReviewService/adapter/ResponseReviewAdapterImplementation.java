package com.project.UberReviewService.adapter;

import com.project.UberReviewService.DTO.CreateRequestDto;
import com.project.UberReviewService.DTO.CreateResponseDto;
import com.project.UberReviewService.modules.Review;
import org.springframework.stereotype.Component;

@Component
public class ResponseReviewAdapterImplementation implements ResponseReviewAdapter{


    @Override
    public CreateResponseDto convertDto(Review responseReview) {
        return CreateResponseDto.builder()
                .content(responseReview.getContent())
                .rating(responseReview.getRating())
                .bookingId(responseReview.getBooking().getId())
                .createdAt(responseReview.getCreatedAt())
                .updatedAt(responseReview.getUpdatedAt()).build();
    }
}
