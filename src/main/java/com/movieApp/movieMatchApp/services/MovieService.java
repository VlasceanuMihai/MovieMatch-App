package com.movieApp.movieMatchApp.services;

import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.mappers.DtoMapper;
import com.movieApp.movieMatchApp.mappers.EntityMapper;
import com.movieApp.movieMatchApp.models.Movie;
import com.movieApp.movieMatchApp.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.movieApp.movieMatchApp.utils.MovieMatchErrorMessages.COULD_NOT_INSERT_MOVIE;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

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

    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    @Transactional
    public MovieDto addMovie(MovieDto movieDto) {

        try {
            MovieDto toBeSaved = movieDto;
            movieRepository.save(entityMapper.toMovie(movieDto));
            return toBeSaved;
        } catch (Exception sqlException) {
            log.warn("Movie could not be added");
            throw new WebApplicationException(Response.status(BAD_REQUEST).entity(COULD_NOT_INSERT_MOVIE).build());
        }


    }
}
