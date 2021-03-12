package com.movieApp.movieMatchApp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

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

    private String year;

    private Double rating;

    private Integer popularity;

    private String imageUrl;
}
