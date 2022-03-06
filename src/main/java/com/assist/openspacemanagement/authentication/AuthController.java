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

    //necesita si compararea parolelor altfel parolele sunt inutile
    //si trebuie verificat daca contul este enabled = true altfel mesaj legat de acest aspect
    @PostMapping("/login")
    public ResponseEntity<String> confirmAuthentification(@RequestBody User user, HttpServletResponse response) {
        return authService.authentication(user,response);
    }

    @GetMapping(value = "/get-user-details")
    public User getUserDetails() {
        return authService.getUserDetails();
    }

}
