package com.movieApp.movieMatchApp.repositories;

import com.movieApp.movieMatchApp.models.movie.Movie;
import com.movieApp.movieMatchApp.models.movie.UserAndMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAndMovieRepository extends JpaRepository<UserAndMovie, Long> {
}
