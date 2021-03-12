package com.movieApp.movieMatchApp.models.movie;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JGlobalMap
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private Integer popularity;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<UserAndMovie> userAndMovie;
}
