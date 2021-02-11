package com.movieApp.movieMatchApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID externalId = UUID.randomUUID();

    @Size(max = 255)
    @Column(nullable = false)
    private String firstName;

    @Size(max = 255)
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Size(min = 5, max = 255)
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
//    @Phone
    @Column(nullable = false, unique = true)
    private String mobileNumber;

    @Column(nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Size(max = 80)
    private String addressLine;

    @Size(min = 2, max = 80)
    @Column(nullable = false)
    private String city;

    @Size(min = 2, max = 80)
    @Column(nullable = false)
    private String country;

    private Instant createdAt;

    @Column
    private boolean emailVerified;

    @Column
    private boolean mobileVerified;
}
