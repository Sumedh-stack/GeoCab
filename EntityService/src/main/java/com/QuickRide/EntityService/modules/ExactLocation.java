package com.QuickRide.EntityService.modules;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExactLocation extends BaseModel{

    @Column(nullable = false)
    Double latitude;

    @Column(nullable = false)
    Double longitude;


}
