package com.movieApp.movieMatchApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class UserAndMovieKey implements Serializable {

    @Column
    private Long userId;

    @Column
    private Long movieId;

}
