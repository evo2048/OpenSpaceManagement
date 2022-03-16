package com.assist.openspacemanagement.authentication;

import com.assist.openspacemanagement.utils.userDetails.CustomUserDetails;
import com.assist.openspacemanagement.utils.userDetails.CustomUserDetailsService;
import com.assist.openspacemanagement.utils.jwt.JwtUtilService;
import com.assist.openspacemanagement.user.User;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtilService jwtUtilService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtilService = jwtUtilService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Object> authentication(User user, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect password.", HttpStatus.UNAUTHORIZED);
        }
        if(!customUserDetailsService.loadUserByUsername(user.getEmail()).isEnabled()) {
            return new ResponseEntity<>("Account disabled.", HttpStatus.LOCKED);
        }

        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(user.getEmail());
        String jwt = jwtUtilService.generateToken(userDetails);

        JSONObject obj = new JSONObject();
        obj.appendField("role",userDetails.getUserEntity().getAuthority().getRole());
        obj.appendField("token",jwt);
        return new ResponseEntity<>(obj, HttpStatus.OK);
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
        return new ResponseEntity<>("Logout with success!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserDetails(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String jwt = null;
        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("JWToken"))
                jwt = cookie.getValue();
        }
        String email = jwtUtilService.extractUsername(jwt);
        CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);
        return new ResponseEntity<>(user.getUserEntity(), HttpStatus.OK);
    }
}
