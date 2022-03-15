package com.assist.openspacemanagement.user;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("management")
public class UserController {
    @Autowired
    private IUserService userService;

    //get status for all user by word
    @GetMapping("admin/user/status")
    public List<User> getAllUserStatus(@RequestParam("q") String filterText){
        return userService.getStatusAllUser(filterText);
    }
    //get status for an employee
    @GetMapping("/user/status")
    public List<JSONObject> getUserStatus(@RequestParam("q") String filterText) {
        return userService.getStatusUserForEmployee(filterText);
    }
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
        return userService.serviceDeactivateUser(id);
    }

    // update for enabled account
    @PutMapping("/user/unlock")
    public ResponseEntity<String> unlockedUser(@RequestParam("id") int id){
        return userService.serviceActivatedUser(id);
    }
}
