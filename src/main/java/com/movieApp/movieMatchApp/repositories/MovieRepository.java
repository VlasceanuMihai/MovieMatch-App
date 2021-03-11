package com.movieApp.movieMatchApp.repositories;

import com.movieApp.movieMatchApp.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    int deleteByName(String name);

    @Query(value = "SELECT m.movie FROM UserAndMovie m WHERE m.user.email = :username")
    List<Movie> findMovies(@Param("username") String username);
}
