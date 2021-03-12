package com.movieApp.movieMatchApp.services.user;

import com.movieApp.movieMatchApp.dao.UserDao;
import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.dto.UserDto;
import com.movieApp.movieMatchApp.mappers.DtoMapper;
import com.movieApp.movieMatchApp.mappers.EntityMapper;
import com.movieApp.movieMatchApp.models.movie.Movie;
import com.movieApp.movieMatchApp.models.movie.UserAndMovie;
import com.movieApp.movieMatchApp.models.movie.UserAndMovieKey;
import com.movieApp.movieMatchApp.models.user.User;
import com.movieApp.movieMatchApp.models.user.UserStatus;
import com.movieApp.movieMatchApp.repositories.UserRepository;
import com.movieApp.movieMatchApp.services.movie.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private EntityMapper entityMapper;
    private DtoMapper dtoMapper;
    private UserDao userDao;
    private MovieService movieService;

    @Autowired
    public UserService(UserRepository userRepository,
                       EntityMapper entityMapper,
                       DtoMapper dtoMapper,
                       UserDao userDao,
                       MovieService movieService) {

        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
        this.userDao = userDao;
        this.movieService = movieService;
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


}
