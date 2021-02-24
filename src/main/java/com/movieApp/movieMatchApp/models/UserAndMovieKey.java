package com.movieApp.movieMatchApp.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class UserAndMovieKey implements Serializable {

    @Column
    private Long userId;

    @Column
    private Long movieId;

}
