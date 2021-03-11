package com.movieApp.movieMatchApp.models.user;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    public boolean isAdmin() {
        return this.equals(ROLE_ADMIN);
    }

    public boolean isUser() {
        return this.equals(ROLE_USER);
    }
}
