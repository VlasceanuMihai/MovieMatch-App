package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Object> getMovies() {

        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<Object> addMovie(MovieDto movieDto) {

        return ResponseEntity.ok(movieService.addMovie(movieDto));
    }
}
