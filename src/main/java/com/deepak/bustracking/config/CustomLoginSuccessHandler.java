package com.deepak.bustracking.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.web.authentication
        .AuthenticationSuccessHandler;

import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomLoginSuccessHandler
        implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(

            HttpServletRequest request,

            HttpServletResponse response,

            Authentication authentication

    ) throws IOException {


        /*
           CHECK THE ROLE OF
           THE LOGGED-IN USER
        */

        for (

                GrantedAuthority authority

                :

                authentication.getAuthorities()

        ) {


            String role =

                    authority.getAuthority();



            /*
               ADMIN LOGIN

               ADMIN
                   ↓
               ADMIN DASHBOARD
            */

            if (

                    role.equals(

                            "ROLE_ADMIN"

                    )

            ) {


                response.sendRedirect(

                        "/admin-dashboard.html"

                );


                return;

            }



            /*
               COORDINATOR LOGIN

               COORDINATOR
                       ↓
               GPS TRIP TRACKER
            */

            if (

                    role.equals(

                            "ROLE_COORDINATOR"

                    )

            ) {


                response.sendRedirect(

                        "/tracker.html"

                );


                return;

            }



            /*
               STUDENT LOGIN

               STUDENT
                   ↓
               STUDENT DASHBOARD
                   ↓
               SELECT BUS
                   ↓
               LIVE MAP
            */

            if (

                    role.equals(

                            "ROLE_STUDENT"

                    )

            ) {


                response.sendRedirect(

                        "/student-dashboard.html"

                );


                return;

            }

        }



        /*
           IF THE USER DOES NOT
           HAVE A VALID ROLE
        */

        response.sendRedirect(

                "/login.html?error=true"

        );

    }

}