package com.movieApp.movieMatchApp.requests;

import com.movieApp.movieMatchApp.validations.ValidationKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDetailsRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 80, message = ValidationKey.SIGNUP_FIRST_NAME_FORMAT)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 80, message = ValidationKey.SIGNUP_LAST_NAME_FORMAT)
    private String lastName;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @Size(min = 5, max = 255)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String mobileNumber;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{2,30}$")
    private String password;

    @NotNull
    @NotEmpty
    @Size(max = 80)
    private String addressLine;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 80)
    private String city;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 80)
    private String country;
}
