package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.requests.UserRegistrationDetailsRequest;
import com.movieApp.movieMatchApp.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/api")
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/v1/registration")
//    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Object> registrationUser(@Valid @RequestBody UserRegistrationDetailsRequest request){
        return ResponseEntity.ok(registrationService.signUp(request));
    }
}
