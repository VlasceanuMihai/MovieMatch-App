package com.movieApp.movieMatchApp.dao;

import com.googlecode.jmapper.JMapper;
import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.models.movie.Movie;
import com.movieApp.movieMatchApp.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieDao {

    public static final JMapper<MovieDto, Movie> TO_MOVIE_DTO = new JMapper<>(MovieDto.class, Movie.class);
    public static final JMapper<Movie, MovieDto> TO_MOVIE_ENTITY = new JMapper<>(Movie.class, MovieDto.class);

    private MovieRepository movieRepository;

    @Autowired
    public MovieDao(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public Optional<MovieDto> findById(Long userId) {
        return this.movieRepository.findById(userId).map(TO_MOVIE_DTO::getDestination);
    }

    @Transactional(readOnly = true)
    public Optional<MovieDto> findByEmail(String name) {
        return this.movieRepository.findByName(name).map(TO_MOVIE_DTO::getDestination);
    }

    @Transactional
    public MovieDto save(MovieDto movieDto) {
        Movie updatedMovie = this.movieRepository.save(TO_MOVIE_ENTITY.getDestination(movieDto));
        return TO_MOVIE_DTO.getDestination(updatedMovie);
    }

    @Transactional
    public void remove(Long movieId) {
        this.movieRepository.deleteById(movieId);
    }
}
