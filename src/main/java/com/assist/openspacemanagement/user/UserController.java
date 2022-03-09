package com.assist.openspacemanagement.user;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management")
public class UserController {
    @Autowired
    private IUserService userService;

    // add new user
    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.serviceAddUser(user);
    }

    //updated user
    @PutMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        return userService.serviceUpdateUser(user);
    }

    //update for disable account
    @PutMapping("/user/lock")
    public ResponseEntity<String> lockUser(@RequestParam("id") int id){
        return userService.serviceDezactivatedUser(id);
    }

    // update for enabled account
    @PutMapping("/user/unlock")
    public ResponseEntity<String> unlockedUser(@RequestParam("id") int id){
        return userService.serviceDezactivatedUser(id);
    }
}
