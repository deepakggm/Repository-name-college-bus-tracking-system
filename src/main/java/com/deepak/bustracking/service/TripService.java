package com.deepak.bustracking.service;

import com.deepak.bustracking.entity.Trip;

import com.deepak.bustracking.repository.BusRepository;
import com.deepak.bustracking.repository.RouteRepository;
import com.deepak.bustracking.repository.TripRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    private final TripRepository
            tripRepository;

    private final BusRepository
            busRepository;

    private final RouteRepository
            routeRepository;


    // Constructor injection

    public TripService(

            TripRepository tripRepository,

            BusRepository busRepository,

            RouteRepository routeRepository

    ) {

        this.tripRepository =
                tripRepository;

        this.busRepository =
                busRepository;

        this.routeRepository =
                routeRepository;
    }


    // Start a new trip

    public Trip startTrip(

            Long busId,

            Long routeId

    ) {

        // Check whether the bus exists

        if (!busRepository.existsById(busId)) {

            throw new RuntimeException(

                    "Bus not found with ID: "

                            + busId

            );
        }


        // Check whether the route exists

        if (!routeRepository.existsById(routeId)) {

            throw new RuntimeException(

                    "Route not found with ID: "

                            + routeId

            );
        }


        // Check whether this bus
        // already has an active trip

        boolean activeTripExists =

                tripRepository

                        .existsByBusIdAndStatus(

                                busId,

                                "ACTIVE"

                        );


        if (activeTripExists) {

            throw new RuntimeException(

                    "Bus ID "

                            + busId

                            + " already has an active trip"

            );
        }


        // Create a new trip

        Trip trip = new Trip();


        // Set bus and route

        trip.setBusId(busId);

        trip.setRouteId(routeId);


        // Set trip status

        trip.setStatus(

                "ACTIVE"

        );


        // Add current starting time

        trip.setStartTime(

                LocalDateTime.now()

        );


        // The trip is still running,
        // so end time must be null

        trip.setEndTime(null);


        // Save the trip

        return tripRepository.save(

                trip

        );
    }


    // Stop an active trip

    public Trip stopTrip(

            Long busId

    ) {

        // Check whether the bus exists

        if (!busRepository.existsById(busId)) {

            throw new RuntimeException(

                    "Bus not found with ID: "

                            + busId

            );
        }


        // Find the active trip
        // for this bus

        Trip activeTrip =

                tripRepository

                        .findTopByBusIdAndStatusOrderByStartTimeDesc(

                                busId,

                                "ACTIVE"

                        )

                        .orElseThrow(

                                () ->

                                        new RuntimeException(

                                                "No active trip found for Bus ID: "

                                                        + busId

                                        )

                        );


        // Change trip status

        activeTrip.setStatus(

                "COMPLETED"

        );


        // Add the ending time

        activeTrip.setEndTime(

                LocalDateTime.now()

        );


        // Save the updated trip

        return tripRepository.save(

                activeTrip

        );
    }


    // Get the active trip
    // of one bus

    public Trip getActiveTrip(

            Long busId

    ) {

        return tripRepository

                .findTopByBusIdAndStatusOrderByStartTimeDesc(

                        busId,

                        "ACTIVE"

                )

                .orElseThrow(

                        () ->

                                new RuntimeException(

                                        "No active trip found for Bus ID: "

                                                + busId

                                )

                );
    }


    // Get all trip records

    public List<Trip> getAllTrips() {

        return tripRepository.findAll();
    }
}