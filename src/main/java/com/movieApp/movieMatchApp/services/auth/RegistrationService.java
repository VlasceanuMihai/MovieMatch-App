package com.movieApp.movieMatchApp.services.auth;

import com.movieApp.movieMatchApp.exceptions.UserExistsException;
import com.movieApp.movieMatchApp.models.user.Role;
import com.movieApp.movieMatchApp.models.user.User;
import com.movieApp.movieMatchApp.models.user.UserStatus;
import com.movieApp.movieMatchApp.repositories.UserRepository;
import com.movieApp.movieMatchApp.requests.UserRegistrationDetailsRequest;
import com.movieApp.movieMatchApp.services.user.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User signUp(UserRegistrationDetailsRequest request) throws UserExistsException{
        if (userService.checkExistingEmail(request.getEmail()) ||
                userService.checkExistingMobileNumber(request.getMobileNumber()) ||
                userService.checkSameNameAndDateOfBirthAndStatus(request.getFirstName(), request.getLastName(), request.getDateOfBirth())) {
            throw new UserExistsException("User with email - " + request.getEmail() + " or mobile number - " + request.getMobileNumber() +
                    " or name - " + (request.getFirstName().concat(request.getLastName())) + " already exists!");
        }

        return userRepository.save(getUpdateUser(request));
    }

    private User getUpdateUser(UserRegistrationDetailsRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddressLine(request.getAddressLine());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());
        user.setRole(Role.ROLE_USER);
        user.setStatus(UserStatus.ACCOUNT_CREATED);
        user.setEmailVerified(false);
        user.setMobileVerified(false);
        return user;
    }
}
