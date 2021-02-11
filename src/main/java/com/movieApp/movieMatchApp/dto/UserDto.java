package com.movieApp.movieMatchApp.dto;

import com.movieApp.movieMatchApp.models.Role;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class UserDto {

    private Long id;

    @NotNull
    private UUID externalId;

    @Size(max = 80)
    private String firstName;

    @Size(max = 80)
    private String lastName;

    private LocalDate dateOfBirth;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 20)
    private String mobileNumber;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotNull
    private Role role;

    private Instant createdAt;

    @Size(max = 80)
    private String addressLine;

    @Size(min = 2, max = 80)
    private String city;

    @Size(min = 2, max = 80)
    private String country;

    private boolean emailVerified;

    private boolean mobileVerified;
}
