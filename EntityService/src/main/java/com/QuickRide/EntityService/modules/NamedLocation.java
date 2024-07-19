package com.QuickRide.EntityService.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class NamedLocation extends BaseModel{

    @OneToOne
    ExactLocation exactLocation;

    String name;

    String zipcode;

    String city;

    String state;

    String country;


}
