package com.movieApp.movieMatchApp.requests;

import com.movieApp.movieMatchApp.validations.ValidationKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDetailsRequest {

    @Size(min = 2, max = 80, message = ValidationKey.SIGNUP_FIRST_NAME_FORMAT)
    @NotNull
    private String firstName;

    @Size(min = 2, max = 80, message = ValidationKey.SIGNUP_LAST_NAME_FORMAT)
    private String lastName;

    @NotNull(message = ValidationKey.SIGNUP_DATE_OF_BIRTH_FORMAT)
    private LocalDate dateOfBirth;

    @Size(min = 5, max = 255)
    @Email
    private String email;

    @NotNull
    @Email
    private String mobileNumber;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{2,30}$")
    private String password;

    @Size(max = 80)
    @NotNull
    private String addressLine;

    @Size(min = 2, max = 80)
    @NotNull
    private String city;

    @Size(min = 2, max = 80)
    @NotNull
    private String country;
}
