package com.QuickRide.EntityService.modules;

import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseModel{

    @Column(unique = true, nullable = false)
    private String plateNumber;

    @ManyToOne
    private Color color;

    private String brand;

    private String model;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    @OneToOne
    private Driver driver;
}