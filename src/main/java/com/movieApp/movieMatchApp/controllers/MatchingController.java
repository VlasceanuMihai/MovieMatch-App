package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.services.MatchingService;
import com.movieApp.movieMatchApp.services.movie.MovieService;
import com.movieApp.movieMatchApp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/matching",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchingController {

    private UserService userService;

    private MovieService movieService;

    private MatchingService matchingService;

    @Autowired
    public MatchingController(UserService userService,
                              MovieService movieService,
                              MatchingService matchingService) {

        this.userService = userService;
        this.movieService = movieService;
        this.matchingService = matchingService;
    }

    @PostMapping(value = "/user/{userId}")
    public ResponseEntity<Object> matchUsers(@PathVariable Long userId,
                                             @RequestBody List<Long> movieList) {

        return ResponseEntity.ok().build();

    }

}
