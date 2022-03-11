package com.assist.openspacemanagement.user;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    // add user
    ResponseEntity<String> serviceAddUser(User user);

    //update an existing user
    ResponseEntity<String> serviceUpdateUser(User user);

    //disable account for a specific user
    ResponseEntity<String> serviceDeactivateUser(int id);

    //enabled account for user
    ResponseEntity<String> serviceActivatedUser(int id);

    //get all status for employee
    List<JSONObject> getStatusUserForEmployee(String filterByWord);

    //get all status for admin
    List<User> getStatusAllUser(String filterByWord);
}
