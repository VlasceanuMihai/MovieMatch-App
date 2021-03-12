package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.dto.UserDto;
import com.movieApp.movieMatchApp.models.user.User;
import com.movieApp.movieMatchApp.security.UserPrincipal;
import com.movieApp.movieMatchApp.services.MatchingService;
import com.movieApp.movieMatchApp.services.movie.MovieService;
import com.movieApp.movieMatchApp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @GetMapping
    public ResponseEntity<Object> matchUsers(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        User user = userService.getUser(userPrincipal.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDto matchingUser = matchingService.match(userService.getUser(userPrincipal.getId()));
        if (matchingUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(matchingUser);
    }

}
