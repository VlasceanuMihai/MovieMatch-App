package com.movieApp.movieMatchApp.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
