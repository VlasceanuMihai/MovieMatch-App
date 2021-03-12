package com.movieApp.movieMatchApp.responses.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDetailsResponse {

    private String email;
    private String mobileNumber;
    private String password;
}
