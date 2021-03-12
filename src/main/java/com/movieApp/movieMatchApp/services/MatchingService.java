package com.movieApp.movieMatchApp.services;

import com.movieApp.movieMatchApp.mappers.EntityMapper;
import com.movieApp.movieMatchApp.services.movie.MovieService;
import com.movieApp.movieMatchApp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {

    MovieService movieService;

    UserService userService;

    EntityMapper entityMapper;

    @Autowired
    public MatchingService(MovieService movieService,
                           UserService userService,
                           EntityMapper entityMapper) {

        this.movieService = movieService;
        this.userService = userService;
        this.entityMapper = entityMapper;
    }


}
