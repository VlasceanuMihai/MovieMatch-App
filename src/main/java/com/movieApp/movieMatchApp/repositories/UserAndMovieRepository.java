package com.movieApp.movieMatchApp.repositories;

import com.movieApp.movieMatchApp.models.movie.UserAndMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAndMovieRepository extends JpaRepository<UserAndMovie, Long> {
}
