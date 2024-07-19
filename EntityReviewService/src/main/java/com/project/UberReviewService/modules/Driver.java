package com.project.UberReviewService.modules;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Driver extends BaseModel{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String licenseNumber;

    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER)
    List<Booking> driverBookings = new ArrayList<>();
}
