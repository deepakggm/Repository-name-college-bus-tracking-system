package com.deepak.bustracking.config;

import com.deepak.bustracking.entity.AppUser;
import com.deepak.bustracking.repository.AppUserRepository;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataInitializer {


    /*
       This method runs automatically
       when Spring Boot starts.
    */

    @Bean
    public CommandLineRunner createDefaultUsers(

            AppUserRepository
                    appUserRepository,

            PasswordEncoder
                    passwordEncoder

    ) {


        return args -> {


            /*
               CREATE ADMIN
            */

            if (

                    !appUserRepository

                            .existsByUsername(

                                    "admin"

                            )

            ) {


                AppUser admin =

                        new AppUser();


                admin.setFullName(

                        "System Administrator"

                );


                admin.setUsername(

                        "admin"

                );


                admin.setEmail(

                        "admin@bustracking.com"

                );


                /*
                   BCrypt encrypts:

                   admin123
                */

                admin.setPassword(

                        passwordEncoder

                                .encode(

                                        "admin123"

                                )

                );


                admin.setRole(

                        "ADMIN"

                );


                admin.setEnabled(

                        true

                );


                appUserRepository.save(

                        admin

                );


                System.out.println(

                        "ADMIN account created"

                );

            }



            /*
               CREATE COORDINATOR
            */

            if (

                    !appUserRepository

                            .existsByUsername(

                                    "coordinator"

                            )

            ) {


                AppUser coordinator =

                        new AppUser();


                coordinator.setFullName(

                        "Bus Coordinator"

                );


                coordinator.setUsername(

                        "coordinator"

                );


                coordinator.setEmail(

                        "coordinator@bustracking.com"

                );


                /*
                   BCrypt encrypts:

                   bus123
                */

                coordinator.setPassword(

                        passwordEncoder

                                .encode(

                                        "bus123"

                                )

                );


                coordinator.setRole(

                        "COORDINATOR"

                );


                coordinator.setEnabled(

                        true

                );


                appUserRepository.save(

                        coordinator

                );


                System.out.println(

                        "COORDINATOR account created"

                );

            }



            /*
               CREATE STUDENT
            */

            if (

                    !appUserRepository

                            .existsByUsername(

                                    "student"

                            )

            ) {


                AppUser student =

                        new AppUser();


                student.setFullName(

                        "Student User"

                );


                student.setUsername(

                        "student"

                );


                student.setEmail(

                        "student@bustracking.com"

                );


                /*
                   BCrypt encrypts:

                   student123
                */

                student.setPassword(

                        passwordEncoder

                                .encode(

                                        "student123"

                                )

                );


                student.setRole(

                        "STUDENT"

                );


                student.setEnabled(

                        true

                );


                appUserRepository.save(

                        student

                );


                System.out.println(

                        "STUDENT account created"

                );

            }


        };

    }

}