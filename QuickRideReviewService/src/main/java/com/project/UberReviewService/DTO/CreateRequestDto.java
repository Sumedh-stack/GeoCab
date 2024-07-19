package com.project.UberReviewService.DTO;

import lombok.Data;

@Data
public class CreateRequestDto {

    private String content;

    private Double rating;

    private Long bookingId;
}
