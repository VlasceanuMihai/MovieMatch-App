package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.security.UserPrincipal;
import com.movieApp.movieMatchApp.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{page}")
    public ResponseEntity<Object> getMovies(@PathVariable Integer page) {
        return ResponseEntity.ok(movieService.getAllMovies(PageRequest.of(page, 10)));
    }

    @GetMapping("/watchlist")
    public ResponseEntity<Object> getMoviesFromWatchlist(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(movieService.getMoviesFromWatchlist(userPrincipal.getId()));
    }

//    @PostMapping("/movie")
//    public ResponseEntity<Object> addMovie(@RequestBody MovieDto movieDto) {
//        return movieService.addMovie(movieDto);
//    }

    @DeleteMapping(path = "/{movieName}")
    public ResponseEntity<Object> removeMovie(@PathVariable String movieName) {
        return movieService.removeMovie(movieName);
    }
}
