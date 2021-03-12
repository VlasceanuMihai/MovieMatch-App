package com.movieApp.movieMatchApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TestMoviesAdderPojo {

    private Long userId;

    private List<Long> moviesIdList;
}
