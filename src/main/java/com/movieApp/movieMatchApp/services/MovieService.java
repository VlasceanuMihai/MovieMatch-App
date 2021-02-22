package com.movieApp.movieMatchApp.services;

import com.movieApp.movieMatchApp.models.Movie;
import com.movieApp.movieMatchApp.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies(){
        return this.movieRepository.findAll();
    }
}
