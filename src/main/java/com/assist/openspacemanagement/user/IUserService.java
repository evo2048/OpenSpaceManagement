package com.assist.openspacemanagement.user;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    public ResponseEntity<String> serviceAddUser(JSONObject jsonObject);
    public ResponseEntity<String> serviceUpdateUser(User user);
    public ResponseEntity<String> serviceDezactivatedUser(int id);
    public ResponseEntity<String> serviceAactivatedUser(int id);
}
