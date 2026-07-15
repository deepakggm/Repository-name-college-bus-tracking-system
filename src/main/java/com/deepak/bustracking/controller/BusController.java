package com.deepak.bustracking.controller;

import com.deepak.bustracking.entity.Bus;
import com.deepak.bustracking.service.BusService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService busService;

    // Constructor injection
    public BusController(BusService busService) {
        this.busService = busService;
    }

    // Add a new bus
    @PostMapping
    public ResponseEntity<Bus> addBus(
            @RequestBody Bus bus
    ) {

        Bus savedBus = busService.addBus(bus);

        return ResponseEntity.ok(savedBus);
    }

    // Get all buses
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {

        return ResponseEntity.ok(
                busService.getAllBuses()
        );
    }

    // Get one bus using ID
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                busService.getBusById(id)
        );
    }

    // Update a bus
    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(
            @PathVariable Long id,
            @RequestBody Bus bus
    ) {

        return ResponseEntity.ok(
                busService.updateBus(id, bus)
        );
    }

    // Delete a bus
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(
            @PathVariable Long id
    ) {

        busService.deleteBus(id);

        return ResponseEntity.ok(
                "Bus deleted successfully"
        );
    }
}