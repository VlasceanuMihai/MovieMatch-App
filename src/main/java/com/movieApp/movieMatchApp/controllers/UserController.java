package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.dto.TestMoviesAdderPojo;
import com.movieApp.movieMatchApp.security.UserPrincipal;
import com.movieApp.movieMatchApp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.getProfile(userPrincipal.getId()));
    }

    @PostMapping("/addMovies")
    public ResponseEntity<Object> addMoviesToUser(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                  @RequestBody List<MovieDto> movieList) {

        return ResponseEntity.ok(userService.addMoviesToUser(userService.getUser(userPrincipal.getId()), movieList));
    }

    // nu stiu daca sa ramana asa, dar fac pt testing purposes ca sa nu stau sa bag dto-uri intregi in postman
    @PostMapping("/addMoviesById")
    public ResponseEntity<Object> addMoviesToUserById(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                      @RequestBody TestMoviesAdderPojo testMoviesAdderPojo) {

        return ResponseEntity.ok(userService.addMoviesToUser(userPrincipal.getId(), testMoviesAdderPojo.getMoviesIdList()));
    }
}
