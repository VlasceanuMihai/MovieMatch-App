package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.requests.UserRegistrationDetailsRequest;
import com.movieApp.movieMatchApp.security.AuthenticationBean;
import com.movieApp.movieMatchApp.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/api")
public class AuthController {

    private RegistrationService registrationService;

    @Autowired
    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/v1/registration")
//    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Object> registrationUser(@Valid @RequestBody UserRegistrationDetailsRequest request) {
        return ResponseEntity.ok(registrationService.signUp(request));
    }

    @GetMapping(path = "/basicauth")
    public ResponseEntity<Object> authenticate() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return ResponseEntity.ok(new AuthenticationBean("You are authenticated"));
    }
}
