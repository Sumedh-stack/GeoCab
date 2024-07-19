package com.project.UberReviewService.adapter;

import com.project.UberReviewService.DTO.CreateResponseDto;
import com.project.UberReviewService.modules.Review;
import org.springframework.stereotype.Component;

@Component
public interface ResponseReviewAdapter {
    CreateResponseDto convertDto(Review requestReview);
}
