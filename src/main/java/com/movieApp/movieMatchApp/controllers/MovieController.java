package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/v1/movies")
    public ResponseEntity<Object> movies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
}
