package com.project.UberReviewService.adapter;

import com.project.UberReviewService.DTO.CreateRequestDto;
import com.project.UberReviewService.modules.Review;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public interface RequestReviewAdapter {
    Review convertDto(CreateRequestDto requestReview);
}
