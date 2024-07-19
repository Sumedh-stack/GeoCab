package com.project.UberReviewService.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger extends BaseModel {


    private String name;

    @OneToMany(mappedBy = "passenger")
    List<Booking> passengerBookings = new ArrayList<>();
}
