package com.deepak.bustracking.service;


import com.deepak.bustracking.entity.Route;

import com.deepak.bustracking.repository
        .RouteRepository;


import org.springframework.stereotype
        .Service;


import java.util.List;


@Service
public class RouteService {


    private final RouteRepository
            routeRepository;



    /*
       CONSTRUCTOR INJECTION
    */

    public RouteService(

            RouteRepository
                    routeRepository

    ) {


        this.routeRepository =

                routeRepository;


    }



    /*
       ADD NEW ROUTE
    */

    public Route addRoute(

            Route route

    ) {


        /*
           ID IS GENERATED
           BY POSTGRESQL
        */

        route.setId(

                null

        );


        return routeRepository.save(

                route

        );


    }



    /*
       GET ALL ROUTES
    */

    public List<Route>
    getAllRoutes() {


        return routeRepository.findAll();


    }



    /*
       GET ONE ROUTE
       USING ROUTE ID
    */

    public Route getRouteById(

            Long id

    ) {


        return routeRepository

                .findById(

                        id

                )

                .orElseThrow(

                        () ->

                                new RuntimeException(

                                        "Route not found "

                                                + "with ID: "

                                                + id

                                )

                );


    }



    /*
       UPDATE ROUTE
    */

    public Route updateRoute(

            Long id,

            Route updatedRoute

    ) {


        /*
           GET EXISTING ROUTE
        */

        Route existingRoute =

                getRouteById(

                        id

                );



        /*
           UPDATE ROUTE NAME
        */

        existingRoute

                .setRouteName(

                        updatedRoute

                                .getRouteName()

                );



        /*
           UPDATE STARTING
           LOCATION
        */

        existingRoute

                .setStartingLocation(

                        updatedRoute

                                .getStartingLocation()

                );



        /*
           UPDATE ENDING
           LOCATION
        */

        existingRoute

                .setEndingLocation(

                        updatedRoute

                                .getEndingLocation()

                );



        /*
           UPDATE DISTANCE
        */

        existingRoute

                .setDistance(

                        updatedRoute

                                .getDistance()

                );



        /*
           SAVE UPDATED ROUTE
        */

        return routeRepository.save(

                existingRoute

        );


    }



    /*
       DELETE ROUTE
    */

    public void deleteRoute(

            Long id

    ) {


        Route route =

                getRouteById(

                        id

                );


        routeRepository.delete(

                route

        );


    }


}