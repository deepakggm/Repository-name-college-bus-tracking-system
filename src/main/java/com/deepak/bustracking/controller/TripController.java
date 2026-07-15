package com.deepak.bustracking.controller;

import com.deepak.bustracking.entity.Trip;
import com.deepak.bustracking.service.TripService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService
            tripService;


    // Constructor injection

    public TripController(

            TripService tripService

    ) {

        this.tripService =
                tripService;
    }


    // Start a new trip

    @PostMapping("/start")
    public ResponseEntity<Trip> startTrip(

            @RequestParam Long busId,

            @RequestParam Long routeId

    ) {

        Trip startedTrip =

                tripService.startTrip(

                        busId,

                        routeId

                );


        return ResponseEntity.ok(

                startedTrip

        );
    }


    // Stop the active trip

    @PutMapping("/stop/{busId}")
    public ResponseEntity<Trip> stopTrip(

            @PathVariable Long busId

    ) {

        Trip completedTrip =

                tripService.stopTrip(

                        busId

                );


        return ResponseEntity.ok(

                completedTrip

        );
    }


    // Get the active trip
    // of a particular bus

    @GetMapping("/active/{busId}")
    public ResponseEntity<Trip> getActiveTrip(

            @PathVariable Long busId

    ) {

        Trip activeTrip =

                tripService.getActiveTrip(

                        busId

                );


        return ResponseEntity.ok(

                activeTrip

        );
    }


    // Get all trip records

    @GetMapping
    public ResponseEntity<List<Trip>>
    getAllTrips() {

        return ResponseEntity.ok(

                tripService.getAllTrips()

        );
    }
}