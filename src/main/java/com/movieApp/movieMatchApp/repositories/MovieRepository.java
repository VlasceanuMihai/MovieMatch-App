package com.movieApp.movieMatchApp.repositories;

import com.movieApp.movieMatchApp.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public void deleteByName(String name);
}
