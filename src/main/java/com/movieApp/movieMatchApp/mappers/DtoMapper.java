package com.movieApp.movieMatchApp.mappers;

import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.dto.UserDto;
import com.movieApp.movieMatchApp.models.movie.Movie;
import com.movieApp.movieMatchApp.models.user.User;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {

    public MovieDto toMovieDto(Movie movie) {

        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .description(movie.getDescription())
                .build();
    }

    public UserDto toUserDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .externalId(user.getExternalId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .addressLine(user.getAddressLine())
                .city(user.getCity())
                .country(user.getCountry())
                .emailVerified(user.isEmailVerified())
                .mobileVerified(user.isMobileVerified())
                .build();
    }
}
