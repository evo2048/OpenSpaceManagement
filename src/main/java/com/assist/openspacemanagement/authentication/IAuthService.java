package com.assist.openspacemanagement.authentication;

import com.assist.openspacemanagement.user.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthService {
    public ResponseEntity<String> authentication(User user, HttpServletResponse response);
    public ResponseEntity<String> logout(HttpServletRequest request,HttpServletResponse response);
    public ResponseEntity<User> getUserDetails(HttpServletRequest request);
}
