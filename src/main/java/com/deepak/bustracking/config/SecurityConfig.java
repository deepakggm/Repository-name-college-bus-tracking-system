package com.deepak.bustracking.config;


import com.deepak.bustracking.service
        .CustomUserDetailsService;


import org.springframework.context.annotation
        .Bean;

import org.springframework.context.annotation
        .Configuration;


import org.springframework.http
        .HttpMethod;


import org.springframework.security.authentication
        .AuthenticationManager;


import org.springframework.security.config.annotation
        .authentication.configuration
        .AuthenticationConfiguration;


import org.springframework.security.config.annotation.web
        .builders.HttpSecurity;


import org.springframework.security.crypto.bcrypt
        .BCryptPasswordEncoder;


import org.springframework.security.crypto.password
        .PasswordEncoder;


import org.springframework.security.web
        .SecurityFilterChain;



@Configuration
public class SecurityConfig {


    private final CustomUserDetailsService
            customUserDetailsService;


    private final CustomLoginSuccessHandler
            customLoginSuccessHandler;



    /*
       CONSTRUCTOR INJECTION
    */

    public SecurityConfig(

            CustomUserDetailsService
                    customUserDetailsService,

            CustomLoginSuccessHandler
                    customLoginSuccessHandler

    ) {


        this.customUserDetailsService =

                customUserDetailsService;


        this.customLoginSuccessHandler =

                customLoginSuccessHandler;

    }



    /*
       BCRYPT PASSWORD ENCODER
    */

    @Bean
    public PasswordEncoder
    passwordEncoder() {


        return new BCryptPasswordEncoder();

    }



    /*
       AUTHENTICATION MANAGER
    */

    @Bean
    public AuthenticationManager
    authenticationManager(

            AuthenticationConfiguration
                    authenticationConfiguration

    ) throws Exception {


        return authenticationConfiguration

                .getAuthenticationManager();

    }



    /*
       COMPLETE SECURITY RULES
    */

    @Bean
    public SecurityFilterChain
    securityFilterChain(

            HttpSecurity http

    ) throws Exception {


        http


                /*
                   LOAD USERS FROM:

                   PostgreSQL
                       ↓
                   app_users
                */

                .userDetailsService(

                        customUserDetailsService

                )



                /*
                   TEMPORARY DEVELOPMENT
                   CONFIGURATION

                   CSRF IS DISABLED
                   DURING DEVELOPMENT.

                   THIS ALLOWS HTML FETCH:

                   POST
                   PUT
                   DELETE

                   BEFORE DEPLOYMENT,
                   WE CAN CONFIGURE
                   CSRF PROPERLY.
                */

                .csrf(

                        csrf ->

                                csrf.disable()

                )



                /*
                   PAGE AND API
                   AUTHORIZATION
                */

                .authorizeHttpRequests(

                        authorization ->

                                authorization



                                        /*
                                           PUBLIC PAGES

                                           LOGIN DOES NOT
                                           REQUIRE A USER.
                                        */

                                        .requestMatchers(

                                                "/",

                                                "/login",

                                                "/login.html",

                                                "/favicon.ico",

                                                "/error",

                                                "/css/**",

                                                "/js/**",

                                                "/images/**"

                                        )

                                        .permitAll()



                                        /*
                                           ADMIN HTML PAGES

                                           ONLY ADMIN CAN
                                           OPEN THESE PAGES.
                                        */

                                        .requestMatchers(

                                                "/admin-dashboard.html",

                                                "/bus-management.html",

                                                "/route-management.html",

                                                "/trip-history.html"

                                        )

                                        .hasRole(

                                                "ADMIN"

                                        )



                                        /*
                                           COORDINATOR
                                           GPS TRACKER PAGE

                                           ADMIN CAN ALSO
                                           OPEN THIS PAGE.
                                        */

                                        .requestMatchers(

                                                "/tracker.html"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR"

                                        )



                                        /*
                                           STUDENT DASHBOARD

                                           ADMIN,
                                           COORDINATOR
                                           AND STUDENT
                                           CAN OPEN IT.
                                        */

                                        .requestMatchers(

                                                "/student-dashboard.html"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR",

                                                "STUDENT"

                                        )



                                        /*
                                           STUDENT LIVE MAP

                                           ALL LOGGED-IN
                                           ROLES CAN VIEW
                                           THE LIVE MAP.
                                        */

                                        .requestMatchers(

                                                "/student-map.html"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR",

                                                "STUDENT"

                                        )



                                        /*
                                           WEBSOCKET

                                           LOGIN IS REQUIRED
                                           TO CONNECT.
                                        */

                                        .requestMatchers(

                                                "/ws",

                                                "/ws/**"

                                        )

                                        .authenticated()



                                        /*
                                           VIEW BUS INFORMATION

                                           GET REQUEST ONLY

                                           ADMIN:
                                           CAN VIEW BUSES

                                           COORDINATOR:
                                           CAN VIEW BUSES

                                           STUDENT:
                                           CAN VIEW BUSES

                                           THIS IS REQUIRED
                                           FOR THE STUDENT
                                           DASHBOARD.
                                        */

                                        .requestMatchers(

                                                HttpMethod.GET,

                                                "/api/buses",

                                                "/api/buses/**"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR",

                                                "STUDENT"

                                        )



                                        /*
                                           ADD NEW BUS

                                           ADMIN ONLY
                                        */

                                        .requestMatchers(

                                                HttpMethod.POST,

                                                "/api/buses",

                                                "/api/buses/**"

                                        )

                                        .hasRole(

                                                "ADMIN"

                                        )



                                        /*
                                           UPDATE BUS

                                           ADMIN ONLY
                                        */

                                        .requestMatchers(

                                                HttpMethod.PUT,

                                                "/api/buses",

                                                "/api/buses/**"

                                        )

                                        .hasRole(

                                                "ADMIN"

                                        )



                                        /*
                                           DELETE BUS

                                           ADMIN ONLY
                                        */

                                        .requestMatchers(

                                                HttpMethod.DELETE,

                                                "/api/buses",

                                                "/api/buses/**"

                                        )

                                        .hasRole(

                                                "ADMIN"

                                        )



                                        /*
                                           VIEW ROUTES

                                           ADMIN,
                                           COORDINATOR
                                           AND STUDENT
                                           CAN VIEW ROUTES.

                                           THIS WILL HELP
                                           WHEN WE DISPLAY
                                           ROUTE NAMES ON
                                           THE STUDENT PAGE.
                                        */

                                        .requestMatchers(

                                                HttpMethod.GET,

                                                "/api/routes",

                                                "/api/routes/**"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR",

                                                "STUDENT"

                                        )



                                        /*
                                           ADD ROUTE

                                           ADMIN ONLY
                                        */

                                        .requestMatchers(

                                                HttpMethod.POST,

                                                "/api/routes",

                                                "/api/routes/**"

                                        )

                                        .hasRole(

                                                "ADMIN"

                                        )



                                        /*
                                           UPDATE ROUTE

                                           ADMIN ONLY
                                        */

                                        .requestMatchers(

                                                HttpMethod.PUT,

                                                "/api/routes",

                                                "/api/routes/**"

                                        )

                                        .hasRole(

                                                "ADMIN"

                                        )



                                        /*
                                           DELETE ROUTE

                                           ADMIN ONLY
                                        */

                                        .requestMatchers(

                                                HttpMethod.DELETE,

                                                "/api/routes",

                                                "/api/routes/**"

                                        )

                                        .hasRole(

                                                "ADMIN"

                                        )



                                        /*
                                           START TRIP

                                           ADMIN AND
                                           COORDINATOR
                                        */

                                        .requestMatchers(

                                                HttpMethod.POST,

                                                "/api/trips/start"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR"

                                        )



                                        /*
                                           STOP TRIP

                                           ADMIN AND
                                           COORDINATOR
                                        */

                                        .requestMatchers(

                                                HttpMethod.PUT,

                                                "/api/trips/stop/**"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR"

                                        )



                                        /*
                                           ACTIVE TRIP STATUS

                                           ALL LOGGED-IN
                                           ROLES CAN CHECK
                                           ACTIVE TRIPS.
                                        */

                                        .requestMatchers(

                                                HttpMethod.GET,

                                                "/api/trips/active/**"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR",

                                                "STUDENT"

                                        )



                                        /*
                                           COMPLETE
                                           TRIP HISTORY

                                           ADMIN ONLY
                                        */

                                        .requestMatchers(

                                                HttpMethod.GET,

                                                "/api/trips"

                                        )

                                        .hasRole(

                                                "ADMIN"

                                        )



                                        /*
                                           VIEW GPS LOCATION

                                           ADMIN,
                                           COORDINATOR
                                           AND STUDENT

                                           STUDENTS REQUIRE
                                           THIS TO VIEW THE
                                           LIVE BUS MAP.
                                        */

                                        .requestMatchers(

                                                HttpMethod.GET,

                                                "/api/locations",

                                                "/api/locations/**"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR",

                                                "STUDENT"

                                        )



                                        /*
                                           SEND GPS LOCATION

                                           ONLY ADMIN AND
                                           COORDINATOR CAN
                                           SEND BUS LOCATION.

                                           STUDENTS CAN VIEW
                                           LOCATION BUT CANNOT
                                           CHANGE LOCATION.
                                        */

                                        .requestMatchers(

                                                HttpMethod.POST,

                                                "/api/locations",

                                                "/api/locations/**"

                                        )

                                        .hasAnyRole(

                                                "ADMIN",

                                                "COORDINATOR"

                                        )



                                        /*
                                           ALL OTHER REQUESTS

                                           USER MUST LOGIN.
                                        */

                                        .anyRequest()

                                        .authenticated()

                )



                /*
                   CUSTOM LOGIN
                */

                .formLogin(

                        form ->

                                form



                                        /*
                                           DISPLAY OUR
                                           CUSTOM LOGIN PAGE
                                        */

                                        .loginPage(

                                                "/login.html"

                                        )



                                        /*
                                           LOGIN FORM
                                           SUBMITS HERE
                                        */

                                        .loginProcessingUrl(

                                                "/login"

                                        )



                                        /*
                                           REDIRECT USER
                                           BASED ON ROLE

                                           ADMIN:
                                           ADMIN DASHBOARD

                                           COORDINATOR:
                                           TRACKER

                                           STUDENT:
                                           STUDENT DASHBOARD
                                        */

                                        .successHandler(

                                                customLoginSuccessHandler

                                        )



                                        /*
                                           WRONG USERNAME
                                           OR PASSWORD
                                        */

                                        .failureUrl(

                                                "/login.html?error=true"

                                        )



                                        /*
                                           ALLOW EVERYONE
                                           TO USE LOGIN.
                                        */

                                        .permitAll()

                )



                /*
                   LOGOUT
                */

                .logout(

                        logout ->

                                logout



                                        /*
                                           LOGOUT URL
                                        */

                                        .logoutUrl(

                                                "/logout"

                                        )



                                        /*
                                           AFTER LOGOUT,
                                           RETURN TO LOGIN.
                                        */

                                        .logoutSuccessUrl(

                                                "/login.html?logout=true"

                                        )



                                        /*
                                           REMOVE LOGIN
                                           SESSION.
                                        */

                                        .invalidateHttpSession(

                                                true

                                        )



                                        /*
                                           DELETE SESSION
                                           COOKIE.
                                        */

                                        .deleteCookies(

                                                "JSESSIONID"

                                        )



                                        /*
                                           ALLOW LOGOUT
                                           REQUEST.
                                        */

                                        .permitAll()

                )



                /*
                   ACCESS DENIED

                   EXAMPLE:

                   STUDENT TRIES
                   TO OPEN:

                   route-management.html
                */

                .exceptionHandling(

                        exception ->

                                exception

                                        .accessDeniedPage(

                                                "/login.html?denied=true"

                                        )

                );


        return http.build();

    }

}