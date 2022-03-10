package com.assist.openspacemanagement.user;

import org.springframework.http.ResponseEntity;

public interface IUserService {
    public ResponseEntity<String> serviceAddUser(User user);
    public ResponseEntity<String> serviceUpdateUser(User user);
    public ResponseEntity<String> serviceDeactivateUser(int id);
    public ResponseEntity<String> serviceActivatedUser(int id);
}
