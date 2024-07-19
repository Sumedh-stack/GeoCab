package com.project.UberReviewService.modules;

import com.project.UberReviewService.Services.BookingService;
import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "bookingreview")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends BaseModel {

    @Column(nullable = false)
    private String content;
    private Double Rating;

    @OneToOne
    @JoinColumn (nullable = false)
    private Booking booking;

}
