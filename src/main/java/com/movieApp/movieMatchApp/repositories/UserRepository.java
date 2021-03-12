package com.movieApp.movieMatchApp.repositories;

import com.movieApp.movieMatchApp.models.user.User;
import com.movieApp.movieMatchApp.models.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumber(String mobileNumber);

    Optional<User> findByFirstNameAndLastNameAndDateOfBirthAndStatus(String firstName,
                                                                     String lastName,
                                                                     LocalDate dateOfBirth,
                                                                     UserStatus status);

    Optional<User> findById(Long id);
}
