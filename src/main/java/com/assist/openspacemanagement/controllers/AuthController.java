package com.assist.openspacemanagement.controllers;

import com.assist.openspacemanagement.entities.UserEntity;
import com.assist.openspacemanagement.jwt.CustomUserDetails;
import com.assist.openspacemanagement.repositories.UserRepository;
import com.assist.openspacemanagement.services.CustomUserDetailsService;
import com.assist.openspacemanagement.services.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthController {

    private CustomUserDetailsService customUserDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtUtilService jwtUtilService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, CustomUserDetailsService customUserDetailsService, JwtUtilService jwtUtilService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtilService = jwtUtilService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/login")
    public Object login(@RequestBody UserEntity user, HttpServletResponse response) throws IOException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        String jwt = jwtUtilService.generateToken(userDetails);

        Cookie cookie = new Cookie("JWToken", jwt);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

        CustomUserDetails customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = customUserDetails.getUserEntity();
        return user;
    }

    @GetMapping("/getDetails")
    public Object getDetails() {
        return "Role based auth works.";
    }

}
