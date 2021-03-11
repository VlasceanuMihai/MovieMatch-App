package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping(value = "/api/v1",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<Object> getMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/movies/{username}")
    public ResponseEntity<Object> getMoviesByUsername(@PathVariable String username){
        return ResponseEntity.ok(movieService.getAllMoviesByUsername(username));

//        try {
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error");
//        }
    }

    @PostMapping("/movie")
    public ResponseEntity<Object> addMovie(@RequestBody MovieDto movieDto) {
        return movieService.addMovie(movieDto);
    }


    @DeleteMapping(path = "/{movieName}")
    public ResponseEntity<Object> removeMovie(@PathVariable String movieName) {
        return movieService.removeMovie(movieName);
    }
}
