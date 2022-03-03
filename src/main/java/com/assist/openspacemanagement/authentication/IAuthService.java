package com.assist.openspacemanagement.authentication;

import com.assist.openspacemanagement.user.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface IAuthService {
    public ResponseEntity<User> authentication(User user, HttpServletResponse response);
    public User getUserDetails();
}
