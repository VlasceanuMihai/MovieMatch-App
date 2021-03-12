package com.movieApp.movieMatchApp.responses.user;

import com.movieApp.movieMatchApp.models.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {

    private String externalId;

    private String fullName;

    private String dateOfBirth;

    private String email;

    private String mobileNumber;

    private UserStatus status;

    private String addressLine;

    private String city;

    private String country;

    private Instant createdAt;

    private boolean emailVerified;

    private boolean mobileVerified;
}
