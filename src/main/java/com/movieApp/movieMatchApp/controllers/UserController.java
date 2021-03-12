package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.repositories.UserRepository;
import com.movieApp.movieMatchApp.security.UserPrincipal;
import com.movieApp.movieMatchApp.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/v1/profile")
    public ResponseEntity<Object> profile(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(userService.getProfile(userPrincipal.getId()));
    }
}
