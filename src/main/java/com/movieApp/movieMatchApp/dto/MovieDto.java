package com.movieApp.movieMatchApp.dto;

import com.movieApp.movieMatchApp.models.movie.UserAndMovie;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = {"name"})
public class MovieDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private Set<UserAndMovie> userAndMovie;
}
