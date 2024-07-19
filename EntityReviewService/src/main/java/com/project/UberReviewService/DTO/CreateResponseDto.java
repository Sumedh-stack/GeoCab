package com.project.UberReviewService.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateResponseDto {

    private String content;

    private Double rating;

    private Long bookingId;

    private Date createdAt;

    private Date updatedAt;

}
