package com.deepak.bustracking.service;

import com.deepak.bustracking.entity.AppUser;
import com.deepak.bustracking.repository.AppUserRepository;

import org.springframework.security.core.authority
        .SimpleGrantedAuthority;

import org.springframework.security.core.userdetails
        .User;

import org.springframework.security.core.userdetails
        .UserDetails;

import org.springframework.security.core.userdetails
        .UserDetailsService;

import org.springframework.security.core.userdetails
        .UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailsService
        implements UserDetailsService {


    private final AppUserRepository
            appUserRepository;


    // Constructor injection

    public CustomUserDetailsService(

            AppUserRepository
                    appUserRepository

    ) {

        this.appUserRepository =
                appUserRepository;
    }


    // Spring Security automatically
    // calls this method during login

    @Override
    public UserDetails loadUserByUsername(

            String username

    ) throws UsernameNotFoundException {


        // Search the username
        // in the PostgreSQL database

        AppUser appUser =

                appUserRepository

                        .findByUsername(

                                username

                        )

                        .orElseThrow(

                                () ->

                                        new UsernameNotFoundException(

                                                "User not found: "

                                                        + username

                                        )

                        );


        // Add ROLE_ before the role name
        //
        // ADMIN
        // becomes
        // ROLE_ADMIN

        String securityRole =

                "ROLE_"

                        + appUser

                        .getRole()

                        .toUpperCase();


        // Convert AppUser into
        // Spring Security UserDetails

        return new User(


                appUser.getUsername(),


                appUser.getPassword(),


                appUser.getEnabled(),


                true,


                true,


                true,


                List.of(

                        new SimpleGrantedAuthority(

                                securityRole

                        )

                )

        );
    }
}