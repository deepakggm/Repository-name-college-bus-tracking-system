package com.deepak.bustracking.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "app_users",
        uniqueConstraints = {

                @UniqueConstraint(
                        columnNames = "username"
                ),

                @UniqueConstraint(
                        columnNames = "email"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;


    // User's full name

    @Column(
            nullable = false
    )
    private String fullName;


    // Used during login

    @Column(
            nullable = false,
            unique = true
    )
    private String username;


    // User's email address

    @Column(
            nullable = false,
            unique = true
    )
    private String email;


    // Password will later be
    // stored using BCrypt encryption

    @Column(
            nullable = false
    )
    private String password;


    // ADMIN
    // COORDINATOR
    // STUDENT

    @Column(
            nullable = false
    )
    private String role;


    // true  → account can log in
    // false → account is disabled

    @Column(
            nullable = false
    )
    private Boolean enabled = true;
}