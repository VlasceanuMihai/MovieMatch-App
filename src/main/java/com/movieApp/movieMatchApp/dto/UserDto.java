package com.movieApp.movieMatchApp.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.movieApp.movieMatchApp.models.movie.UserAndMovie;
import com.movieApp.movieMatchApp.models.user.Role;
import com.movieApp.movieMatchApp.models.user.UserStatus;
import lombok.*;

import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@JGlobalMap
public class UserDto {

    private Long id;

    @NotNull
    @NotEmpty
    private UUID externalId;

    @NotNull
    @NotEmpty
    @Size(max = 80)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max = 80)
    private String lastName;

    @NotNull
    @NotEmpty
    @Past
    private LocalDate dateOfBirth;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 20)
    private String mobileNumber;

    @NotBlank
    @NotNull
    @Size(max = 100)
    private String password;

    @NotNull
    private Role role;

    @NotNull
    private UserStatus status;

    @Size(max = 80)
    private String addressLine;

    private Instant createdAt;

    @Size(min = 2, max = 80)
    private String city;

    @Size(min = 2, max = 80)
    private String country;

    private boolean emailVerified;

    private boolean mobileVerified;

    private Set<UserAndMovie> userAndMovie;

    public String getFullName() {
        return Stream.of(firstName, lastName)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "))
                .trim().replaceAll(" +", " ");
    }
}
