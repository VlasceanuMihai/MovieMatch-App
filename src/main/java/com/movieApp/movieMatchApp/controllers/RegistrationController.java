package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.requests.UserRegistrationDetailsRequest;
import com.movieApp.movieMatchApp.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/v1/registration")
//    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Object> registrationUser(@Valid @RequestBody UserRegistrationDetailsRequest request){
        return ResponseEntity.ok(registrationService);
    }
}
