package com.deepak.bustracking.repository;

import com.deepak.bustracking.entity.Trip;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository
        extends JpaRepository<Trip, Long> {

    // Find the active trip
    // of a particular bus

    Optional<Trip>
    findTopByBusIdAndStatusOrderByStartTimeDesc(
            Long busId,
            String status
    );


    // Check whether a bus
    // already has an active trip

    boolean existsByBusIdAndStatus(
            Long busId,
            String status
    );
}