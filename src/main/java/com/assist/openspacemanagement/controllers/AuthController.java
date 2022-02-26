package com.assist.openspacemanagement.controllers;

import com.assist.openspacemanagement.entities.AuthorityEntity;
import com.assist.openspacemanagement.entities.UserEntity;
import com.assist.openspacemanagement.repositories.AuthorityRepository;
import com.assist.openspacemanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class AuthController {

    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @PostMapping("/login")
    public Optional<UserEntity> login(@RequestBody UserEntity user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            return Optional.of(userRepository.findByEmail(user.getEmail()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password.", e);
        }
//        return Optional.empty();
    }
    @PostMapping("/admin/addUser")
    public boolean addUser(@RequestBody UserEntity user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity)auth.getPrincipal();
        user.setActivated(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(userEntity.getAuthority());
        return true;
    }
    @GetMapping("/getDetails")
    public UserEntity getDetails() {

//        AuthorityEntity authorityEntity = new AuthorityEntity();
//        authorityEntity.setRole("ADMIN");
//        authorityRepository.save(authorityEntity);
//        AuthorityEntity authorityEntity1 = new AuthorityEntity();
//        authorityEntity1.setRole("OFFICE");
//        authorityRepository.save(authorityEntity1);
//        AuthorityEntity authorityEntity2 = new AuthorityEntity();
//        authorityEntity2.setRole("USER");
//        authorityRepository.save(authorityEntity2);
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setActivated(true);
//        userEntity.setEmail("enemircea20@yahoo.com");
//        userEntity.setPassword("password");
//        userEntity.setGender("Male");
//        userEntity.setFristName("Mircea");
//        userEntity.setLastName("Ene");
//        authorityEntity = authorityRepository.findAuthorityEntityByRole("ADMIN");
//        userEntity.setAuthority(authorityEntity);
//        userRepository.save(userEntity);

        UserEntity user = userRepository.findByEmail("enemircea20@yahoo.com");
        return user;
    }

}
