package com.deepak.bustracking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;


    // Bus used for this trip

    @Column(
            nullable = false
    )
    private Long busId;


    // Route selected for this trip

    @Column(
            nullable = false
    )
    private Long routeId;


    // Example:
    // ACTIVE
    // COMPLETED

    @Column(
            nullable = false
    )
    private String status;


    // Time when the coordinator
    // starts the trip

    @Column(
            nullable = false
    )
    private LocalDateTime startTime;


    // Time when the coordinator
    // stops the trip

    private LocalDateTime endTime;
}