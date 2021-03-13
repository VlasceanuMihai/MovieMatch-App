package com.movieApp.movieMatchApp.models.movie;

import com.movieApp.movieMatchApp.models.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_and_movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserAndMovie {

    @EmbeddedId
    UserAndMovieKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
