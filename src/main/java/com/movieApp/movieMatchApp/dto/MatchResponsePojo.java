package com.movieApp.movieMatchApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MatchResponsePojo {

    private UserDto userDto;

    private Set<MovieDto> movieSet;
}
