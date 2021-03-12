package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.repositories.UserRepository;
import com.movieApp.movieMatchApp.security.UserPrincipal;
import com.movieApp.movieMatchApp.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.dto.MoviesAdder;
import com.movieApp.movieMatchApp.dto.UserDto;
import com.movieApp.movieMatchApp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(userService.getProfile(userPrincipal.getId()));
    }

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/addMovies")
    public ResponseEntity<Object> addMoviesToUser(@RequestBody UserDto userDto,
                                                  @RequestBody List<MovieDto> movieList) {

        return ResponseEntity.ok(userService.addMoviesToUser(userDto, movieList));
    }

    @PostMapping("/addMoviesById")
    public ResponseEntity<Object> addMoviesToUserById(@RequestBody MoviesAdder moviesAdder) {

        return ResponseEntity.ok(userService.addMoviesToUser(moviesAdder.getUserId(), moviesAdder.getMoviesIdList()));
    }
}
