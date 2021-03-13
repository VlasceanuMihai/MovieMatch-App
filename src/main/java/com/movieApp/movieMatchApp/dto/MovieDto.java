package com.movieApp.movieMatchApp.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = {"name"})
@JGlobalMap
public class MovieDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String year;

    private Double rating;

    private Integer popularity;

    private String imageUrl;
}
