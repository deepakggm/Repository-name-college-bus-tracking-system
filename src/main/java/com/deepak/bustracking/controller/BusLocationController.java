package com.deepak.bustracking.controller;

import com.deepak.bustracking.entity.BusLocation;
import com.deepak.bustracking.service.BusLocationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class BusLocationController {

    private final BusLocationService
            busLocationService;

    // Constructor injection
    public BusLocationController(
            BusLocationService busLocationService
    ) {
        this.busLocationService =
                busLocationService;
    }

    // Receive and save GPS location
    @PostMapping
    public ResponseEntity<BusLocation>
    updateLocation(
            @RequestBody
            BusLocation busLocation
    ) {

        return ResponseEntity.ok(
                busLocationService
                        .updateLocation(busLocation)
        );
    }

    // Get the latest location of a bus
    @GetMapping("/bus/{busId}")
    public ResponseEntity<BusLocation>
    getLatestLocation(
            @PathVariable Long busId
    ) {

        return ResponseEntity.ok(
                busLocationService
                        .getLatestLocation(busId)
        );
    }

    // Get complete location history
    @GetMapping("/history")
    public ResponseEntity<List<BusLocation>>
    getAllLocationHistory() {

        return ResponseEntity.ok(
                busLocationService
                        .getAllLocationHistory()
        );
    }
}