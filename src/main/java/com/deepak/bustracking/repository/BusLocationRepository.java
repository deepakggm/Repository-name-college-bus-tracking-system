package com.deepak.bustracking.repository;

import com.deepak.bustracking.entity.BusLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusLocationRepository
        extends JpaRepository<BusLocation, Long> {

    Optional<BusLocation>
    findTopByBusIdOrderByUpdatedAtDesc(Long busId);
}