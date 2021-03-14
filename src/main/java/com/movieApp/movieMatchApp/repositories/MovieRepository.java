package com.movieApp.movieMatchApp.repositories;

import com.movieApp.movieMatchApp.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    int deleteByName(String name);

    @Query(value = "SELECT m.movie FROM UserAndMovie m WHERE m.user.id = :userId")
    Set<Movie> findMoviesById(@Param("userId") Long userId);

    @Query(value = "SELECT m.movie FROM UserAndMovie m WHERE m.user.id = :userId AND m.movie.id = :movieId")
    Optional<Movie> findMovieByUserIdAndMovieId(@Param("userId") Long userId, @Param("movieId") Long movieId);

    Optional<Movie> findByName(String name);

    Optional<Movie> findMovieById(Long movieId);
}
