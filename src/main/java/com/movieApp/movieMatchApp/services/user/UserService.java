package com.movieApp.movieMatchApp.services.user;

import com.movieApp.movieMatchApp.models.user.User;
import com.movieApp.movieMatchApp.models.user.UserStatus;
import com.movieApp.movieMatchApp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

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

    public User getUser(Long id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    public Optional<UserDto> addMoviesToUser(UserDto userDto, List<MovieDto> movieDtoList) {


        User user = entityMapper.toUser(userDto);
        Set<Movie> movies = movieDtoList.stream()
                .map(movieDto -> entityMapper.toMovie(movieDto))
                .collect(Collectors.toSet());

        Set<UserAndMovie> userAndMovieSet = new HashSet<>();
        movies.forEach(movie -> {
            UserAndMovie userAndMovie = new UserAndMovie(new UserAndMovieKey(user.getId(), movie.getId()), user, movie);
            userAndMovieSet.add(userAndMovie);
        });
        user.setUserAndMovie(userAndMovieSet);
        userRepository.save(user);
        return Optional.of(dtoMapper.toUserDto(user));
    }

    // nu stiu daca sa ramana asa, dar fac pt testing purposes ca sa nu stau sa bag dto-uri intregi in postman
    public Optional<UserDto> addMoviesToUser(Long userId, List<Long> movieIdList) {

        User user = entityMapper.toUser(userDao.findById(userId).get());

        Set<Movie> movies = movieIdList.stream()
                .map(movieId -> movieService.getMovie(movieId))
                .collect(Collectors.toSet());

        Set<UserAndMovie> userAndMovieSet = new HashSet<>();
        movies.forEach(movie -> {
            UserAndMovie userAndMovie = new UserAndMovie(new UserAndMovieKey(userId, movie.getId()), user, movie);
            userAndMovieSet.add(userAndMovie);
        });
        user.setUserAndMovie(userAndMovieSet);
        userRepository.save(user);
        return Optional.of(dtoMapper.toUserDto(user));
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
