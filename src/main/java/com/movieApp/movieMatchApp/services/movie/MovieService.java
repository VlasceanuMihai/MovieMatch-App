package com.movieApp.movieMatchApp.services.movie;

import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.exceptions.movie.MovieNotFoundException;
import com.movieApp.movieMatchApp.mappers.DtoMapper;
import com.movieApp.movieMatchApp.mappers.EntityMapper;
import com.movieApp.movieMatchApp.models.movie.Movie;
import com.movieApp.movieMatchApp.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.movieApp.movieMatchApp.utils.MovieMatchErrorMessages.*;

@Service
@Slf4j
public class MovieService {

    private MovieRepository movieRepository;
    private EntityMapper entityMapper;
    private DtoMapper dtoMapper;

    @Autowired
    public MovieService(MovieRepository movieRepository,
                        EntityMapper entityMapper,
                        DtoMapper dtoMapper) {

        this.movieRepository = movieRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    public List<MovieDto> getAllMovies() {
        return this.movieRepository.findAll().stream()
                .map(movie -> dtoMapper.toMovieDto(movie))
                .collect(Collectors.toList());
    }

    public List<MovieDto> getAllMoviesByUsername(String username) {
        return this.movieRepository.findMovies(username).stream()
                .map(movie -> dtoMapper.toMovieDto(movie))
                .collect(Collectors.toList());
    }

    public ResponseEntity<Object> addMovie(MovieDto movieDto) {
        try {
            movieRepository.save(entityMapper.toMovie(movieDto));
            return ResponseEntity.ok(movieDto);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return ResponseEntity.badRequest().body(MOVIE_ALREADY_INSERTED);
        } catch (Exception e) {
            log.warn("Movie could not be added", e);
            return ResponseEntity.badRequest().body(COULD_NOT_INSERT_MOVIE);
        }
    }

    public ResponseEntity<Object> removeMovie(String movieName) {
        try {
            movieRepository.deleteByName(movieName);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.warn("Movie could not be added", e);
            return ResponseEntity.badRequest().body(COULD_NOT_DELETE_MOVIE);
        }
    }

    public Movie getMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found!"));
    }
}
