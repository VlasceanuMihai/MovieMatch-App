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
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
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

    @GetMapping
    public ResponseEntity<Object> getUsers() {

        return ResponseEntity.ok(userService.getAllDtoUsers());
    }

    @PostMapping("/addMovies")
    public ResponseEntity<Object> addMoviesToUser(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                  @RequestBody List<MovieDto> movieList) {

        Set<MovieDto> movieDtoSet = userService.addMoviesToUser(userService.getUser(userPrincipal.getId()), movieList);

        if (movieDtoSet == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(movieDtoSet);
    }

    // nu stiu daca sa ramana asa, dar fac pt testing purposes ca sa nu stau sa bag dto-uri intregi in postman
    @PostMapping("/addMoviesById")
    public ResponseEntity<Object> addMoviesToUserById(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                      @RequestBody TestMoviesAdderPojo testMoviesAdderPojo) {

        Set<MovieDto> movieDtoSet = userService.addMoviesToUser(userPrincipal.getId(), testMoviesAdderPojo.getMoviesIdList());

        if (movieDtoSet == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(movieDtoSet);
    }
}
