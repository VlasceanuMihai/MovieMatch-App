package com.movieApp.movieMatchApp.controllers;

import com.movieApp.movieMatchApp.exceptions.UserExistsException;
import com.movieApp.movieMatchApp.requests.UserRegistrationDetailsRequest;
import com.movieApp.movieMatchApp.services.auth.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class AuthController {

    private RegistrationService registrationService;

    @Autowired
    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
//    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Object> registrationUser(@Valid @RequestBody UserRegistrationDetailsRequest request) {
        try {
            return ResponseEntity.ok(registrationService.signUp(request));
        } catch (UserExistsException e) {
            return ResponseEntity.badRequest().body("User with email: " + request.getEmail() + " or mobile number: " + request.getMobileNumber() +
                    " or name: " + (request.getFirstName().concat(request.getLastName())) + " already exists!");
        }
    }
}
