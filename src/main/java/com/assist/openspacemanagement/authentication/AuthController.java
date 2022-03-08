package com.assist.openspacemanagement.authentication;

import com.assist.openspacemanagement.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("office")
public class AuthController {
    @Autowired
    private IAuthService authService;


    @GetMapping("/logout")
    public ResponseEntity<String> logoutRequest(HttpServletRequest request,HttpServletResponse response){
        return authService.logout(request,response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> confirmAuthentication(@RequestBody User user, HttpServletResponse response) {
        return authService.authentication(user,response);
    }

    @GetMapping(value = "/get-user-details")
    public ResponseEntity<User> getUserDetails(HttpServletRequest request) { return authService.getUserDetails(request); }

}
