package com.assist.openspacemanagement.authentication;

import com.assist.openspacemanagement.security.CustomUserDetails;
import com.assist.openspacemanagement.security.CustomUserDetailsService;
import com.assist.openspacemanagement.security.jwt.JwtUtilService;
import com.assist.openspacemanagement.user.User;
import com.assist.openspacemanagement.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthService implements IAuthService{

    private CustomUserDetailsService customUserDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtUtilService jwtUtilService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, CustomUserDetailsService customUserDetailsService, JwtUtilService jwtUtilService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtilService = jwtUtilService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> authentication(User user, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        String jwt = jwtUtilService.generateToken(userDetails);

        Cookie cookie = new Cookie("JWToken", jwt);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

        //urmatoarea linie genereaza cast exception
        /*
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = customUserDetails.getUserEntity();
        */
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        return new ResponseEntity<>(customUserDetails.getUserEntity().getAuthority().getRole(), HttpStatus.OK);
    }

    //delete cookies
    @Override
    public ResponseEntity<String> logout(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return new ResponseEntity<>("Logout with succes!",HttpStatus.OK);
    }

    @Override
    public User getUserDetails() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getUserEntity();
    }
}
