package com.movieApp.movieMatchApp.services.user;

import com.movieApp.movieMatchApp.dao.UserDao;
import com.movieApp.movieMatchApp.dto.UserDto;
import com.movieApp.movieMatchApp.exceptions.UserNotFoundException;
import com.movieApp.movieMatchApp.models.user.User;
import com.movieApp.movieMatchApp.models.user.UserStatus;
import com.movieApp.movieMatchApp.repositories.UserRepository;
import com.movieApp.movieMatchApp.responses.user.UserProfileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private static final String DATE_FORMAT = "d/MM/yyyy";

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkExistingEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean checkExistingMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber).isPresent();
    }

    public boolean checkSameNameAndDateOfBirthAndStatus(String firstName, String lastName, LocalDate dateOfBirth) {
        Optional<User> existingUser = userRepository.findByFirstNameAndLastNameAndDateOfBirthAndStatus(
                firstName,
                lastName,
                dateOfBirth,
                UserStatus.ACTIVE);
        return existingUser.isPresent();
    }

    @Transactional
    public UserProfileResponse getProfile(Long id) {
        User userEntity = findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDto user = UserDao.TO_USER_DTO.getDestination(userEntity);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        return UserProfileResponse.builder()
                .externalId(user.getExternalId().toString())
                .fullName(user.getFullName())
                .dateOfBirth(user.getDateOfBirth().format(formatter))
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .status(user.getStatus())
                .addressLine(user.getAddressLine())
                .city(user.getCity())
                .country(user.getCountry())
                .createdAt(user.getCreatedAt())
                .mobileVerified(user.isMobileVerified())
                .build();
    }

    @Transactional
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }
}
