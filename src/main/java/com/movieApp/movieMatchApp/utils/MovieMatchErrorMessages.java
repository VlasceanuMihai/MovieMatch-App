package com.movieApp.movieMatchApp.utils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MovieMatchErrorMessages implements Serializable {

    MOVIE_ALREADY_INSERTED("The entered movie is already in our database."),

    COULD_NOT_INSERT_MOVIE("Movie could not be inserted"),
    COULD_NOT_INSERT_USER("User could not be inserted"),
    COULD_NOT_DELETE_MOVIE("Movie could not be deleted");


    private final String errorMessage;

    MovieMatchErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return this.name();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
