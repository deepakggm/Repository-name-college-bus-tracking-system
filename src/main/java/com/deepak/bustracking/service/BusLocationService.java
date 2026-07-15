package com.deepak.bustracking.service;

import com.deepak.bustracking.entity.BusLocation;
import com.deepak.bustracking.repository.BusLocationRepository;
import com.deepak.bustracking.repository.BusRepository;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusLocationService {

    private final BusLocationRepository
            busLocationRepository;

    private final BusRepository
            busRepository;

    private final SimpMessagingTemplate
            messagingTemplate;


    // Constructor injection
    public BusLocationService(

            BusLocationRepository
                    busLocationRepository,

            BusRepository
                    busRepository,

            SimpMessagingTemplate
                    messagingTemplate

    ) {

        this.busLocationRepository =
                busLocationRepository;

        this.busRepository =
                busRepository;

        this.messagingTemplate =
                messagingTemplate;
    }


    // Save a new GPS location
    // and immediately send it
    // to all connected students

    public BusLocation updateLocation(

            BusLocation busLocation

    ) {

        // Check whether the bus exists

        boolean busExists =

                busRepository.existsById(

                        busLocation.getBusId()

                );


        if (!busExists) {

            throw new RuntimeException(

                    "Bus not found with ID: "

                            + busLocation.getBusId()

            );
        }


        // PostgreSQL automatically
        // generates a new ID

        busLocation.setId(null);


        // Add the current date and time

        busLocation.setUpdatedAt(

                LocalDateTime.now()

        );


        // Save the GPS location
        // in PostgreSQL

        BusLocation savedLocation =

                busLocationRepository.save(

                        busLocation

                );


        // Immediately send the new location
        // to students using WebSocket

        messagingTemplate.convertAndSend(

                "/topic/bus/"

                        + savedLocation.getBusId(),

                savedLocation

        );


        // Return the saved location

        return savedLocation;
    }


    // Get the latest location
    // of one bus

    public BusLocation getLatestLocation(

            Long busId

    ) {

        // Check whether the bus exists

        if (

                !busRepository.existsById(

                        busId

                )

        ) {

            throw new RuntimeException(

                    "Bus not found with ID: "

                            + busId

            );
        }


        // Return the newest GPS location

        return busLocationRepository

                .findTopByBusIdOrderByUpdatedAtDesc(

                        busId

                )

                .orElseThrow(

                        () ->

                                new RuntimeException(

                                        "No location found for bus ID: "

                                                + busId

                                )

                );
    }


    // Get the complete
    // GPS location history

    public List<BusLocation>

    getAllLocationHistory() {

        return busLocationRepository

                .findAll();
    }
}