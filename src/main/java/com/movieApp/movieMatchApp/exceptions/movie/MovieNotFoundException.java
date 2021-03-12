package com.movieApp.movieMatchApp.exceptions.movie;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException() {
    }

    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
