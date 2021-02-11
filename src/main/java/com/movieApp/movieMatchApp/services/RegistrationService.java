package com.movieApp.movieMatchApp.services;

import com.movieApp.movieMatchApp.exceptions.UserExistsException;
import com.movieApp.movieMatchApp.models.Role;
import com.movieApp.movieMatchApp.models.User;
import com.movieApp.movieMatchApp.models.UserStatus;
import com.movieApp.movieMatchApp.repositories.UserRepository;
import com.movieApp.movieMatchApp.requests.UserRegistrationDetailsRequest;
import com.movieApp.movieMatchApp.responses.UserRegistrationDetailsResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Slf4j
public class RegistrationService {

    private UserRepository userRepository;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @SneakyThrows
    public User signUp(UserRegistrationDetailsRequest request) {
        if (userService.checkExistingEmail(request.getEmail()) ||
                userService.checkExistingMobileNumber(request.getMobileNumber()) ||
                userService.checkSameNameAndDateOfBirthAndStatus(request.getFirstName(), request.getLastName(), request.getDateOfBirth())) {
            throw new UserExistsException("User with email " + request.getEmail() + " already exists!");
        }

        return userRepository.save(getUpdateUser(request));
    }

    private User getUpdateUser(UserRegistrationDetailsRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .addressLine(request.getAddressLine())
                .city(request.getCity())
                .country(request.getCountry())
                .role(Role.ROLE_USER)
                .status(UserStatus.ACCOUNT_CREATED)
                .createdAt(Instant.now())
                .emailVerified(false)
                .mobileVerified(false)
                .build();
    }
}
