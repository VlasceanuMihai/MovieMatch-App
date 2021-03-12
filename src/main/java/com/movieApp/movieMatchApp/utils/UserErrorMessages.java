package com.movieApp.movieMatchApp.utils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserErrorMessages implements Serializable {

    EMAIL_OR_PHONE_NO_ALREADY_USED("The entered email or phone number is already in use."),

    COULD_NOT_INSERT_MOVIE("Movie could not be inserted"),
    COULD_NOT_INSERT_USER("User could not be inserted"),
    COULD_NOT_DELETE_MOVIE("Movie could not be deleted");


    private final String errorMessage;

    UserErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return this.name();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
