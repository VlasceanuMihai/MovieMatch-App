package com.movieApp.movieMatchApp.mappers;

import com.movieApp.movieMatchApp.dto.MovieDto;
import com.movieApp.movieMatchApp.dto.UserDto;
import com.movieApp.movieMatchApp.models.movie.Movie;
import com.movieApp.movieMatchApp.models.user.User;
import org.springframework.stereotype.Service;

@Service
public class EntityMapper {

    public Movie toMovie(MovieDto movieDto) {

        return Movie.builder()
                .id(movieDto.getId())
                .name(movieDto.getName())
                .description(movieDto.getDescription())
                .build();
    }

    public User toUser(UserDto userDto) {

        return User.builder()
                .id(userDto.getId())
                .externalId(userDto.getExternalId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .dateOfBirth(userDto.getDateOfBirth())
                .email(userDto.getEmail())
                .mobileNumber(userDto.getMobileNumber())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .status(userDto.getStatus())
                .createdAt(userDto.getCreatedAt())
                .addressLine(userDto.getAddressLine())
                .city(userDto.getCity())
                .country(userDto.getCountry())
                .emailVerified(userDto.isEmailVerified())
                .mobileVerified(userDto.isMobileVerified())
                .build();
    }
}
