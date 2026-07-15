package com.deepak.bustracking.repository;

import com.deepak.bustracking.entity.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository
        extends JpaRepository<AppUser, Long> {


    // Find a user using username
    // This method will be used
    // during login

    Optional<AppUser> findByUsername(
            String username
    );


    // Check whether a username
    // is already registered

    boolean existsByUsername(
            String username
    );


    // Check whether an email
    // is already registered

    boolean existsByEmail(
            String email
    );
}