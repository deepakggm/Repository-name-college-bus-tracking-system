package com.deepak.bustracking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bus_locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusLocation {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private Long busId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    private String locationName;

    private LocalDateTime updatedAt;
}