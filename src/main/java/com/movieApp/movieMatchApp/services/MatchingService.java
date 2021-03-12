package com.movieApp.movieMatchApp.services;

import com.movieApp.movieMatchApp.dao.UserDao;
import com.movieApp.movieMatchApp.dto.UserDto;
import com.movieApp.movieMatchApp.mappers.EntityMapper;
import com.movieApp.movieMatchApp.models.user.User;
import com.movieApp.movieMatchApp.services.movie.MovieService;
import com.movieApp.movieMatchApp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchingService {

    MovieService movieService;

    UserService userService;

    EntityMapper entityMapper;

    UserDao userDao;

    @Autowired
    public MatchingService(MovieService movieService,
                           UserService userService,
                           EntityMapper entityMapper,
                           UserDao userDao) {

        this.movieService = movieService;
        this.userService = userService;
        this.entityMapper = entityMapper;
        this.userDao = userDao;
    }

    public UserDto match(User user) {

        List<Long> userMovieListId = user.getUserAndMovie().stream()
                .map(userAndMovie -> userAndMovie.getMovie().getId())
                .collect(Collectors.toList());

        List<User> users = userService.getAllUsers();

        Map<Long, Integer> results = new HashMap<>();
        users.forEach(testingUser -> {
            if (!testingUser.getId().equals(user.getId())) {
                List<Long> testingUserMovieList = testingUser.getUserAndMovie().stream()
                        .map(userAndMovie -> userAndMovie.getMovie().getId())
                        .collect(Collectors.toList());
                testingUserMovieList.retainAll(userMovieListId);
                results.put(testingUser.getId(), testingUserMovieList.size());
            }
        });

        if (results.isEmpty()) {
            return null;
        }

        int maxValueInMap = (Collections.max(results.values()));
        Long matchingId = null;
        for (Map.Entry<Long, Integer> entry : results.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                matchingId = entry.getKey();
            }
        }

        if (matchingId == null) {
            return null;
        }

        Optional<UserDto> matchedUser = userDao.findById(matchingId);

        if (matchedUser.isEmpty()) {
            return null;
        }

        return matchedUser.get();
    }

}
