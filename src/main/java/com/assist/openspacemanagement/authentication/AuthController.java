package com.assist.openspacemanagement.authentication;

import com.assist.openspacemanagement.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
    @Autowired
    private IAuthService authService;

    @GetMapping("/auth")
    public ResponseEntity<User> confirmAuthentification(@RequestBody User user, HttpServletResponse response) {
        return authService.authentication(user,response);
    }

    @GetMapping(value = "/get-user-details")
    public User getUserDetails() {
        return authService.getUserDetails();
    }


}
