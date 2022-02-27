package com.assist.openspacemanagement.controllers;

import com.assist.openspacemanagement.entities.UserEntity;
import com.assist.openspacemanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/login")
    public UserEntity login(@RequestBody UserEntity user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password.", e);
        }
        return userRepository.findByEmail(user.getEmail());
    }
    @GetMapping("/getDetails")
    public UserEntity getDetails() {
        //TODO JWT
        return null;
    }

}
