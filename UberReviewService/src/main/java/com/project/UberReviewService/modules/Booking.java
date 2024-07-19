package com.project.UberReviewService.modules;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Booking extends BaseModel {

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;


    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    private long totalDistance;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingStatus=" + bookingStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalDistance=" + totalDistance +
                  '}';
    }

}
