package com.movieApp.movieMatchApp.security.resource;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -5616176897013108345L;

    private String username;
    private String password;

    public LoginRequest() {
        super();
    }

    public LoginRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
}
