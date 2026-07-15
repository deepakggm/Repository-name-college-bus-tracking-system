package com.deepak.bustracking.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "routes")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Route {


    /*
       PRIMARY KEY
    */

    @Id

    @GeneratedValue(

            strategy =
                    GenerationType.IDENTITY

    )

    private Long id;



    /*
       ROUTE NAME

       Example:

       Belukurichi College Route
    */

    @Column(

            nullable = false,

            unique = true

    )

    private String routeName;



    /*
       STARTING LOCATION

       Example:

       Belukurichi
    */

    @Column(

            nullable = false

    )

    private String startingLocation;



    /*
       ENDING LOCATION

       Example:

       Kongunadu College
    */

    @Column(

            nullable = false

    )

    private String endingLocation;



    /*
       TOTAL ROUTE DISTANCE

       Example:

       35.5 KM
    */

    @Column
    private Double distance;


}