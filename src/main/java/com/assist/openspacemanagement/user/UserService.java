package com.assist.openspacemanagement.user;

import com.assist.openspacemanagement.utils.Diverse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> serviceAddUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User add with successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> serviceUpdateUser(User user) {
        try{
            userRepository.save(user);
        }catch (Exception e){
            return new ResponseEntity<>("User not found!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Update user with successfully",HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<String> serviceDezactivatedUser(int id) {
        try{
            User user = userRepository.getById(id);
            user.setAccountEnabled(false);
            userRepository.save(user);
        }catch (Exception e){
            new ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Operation successfully!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> serviceAactivatedUser(int id) {
        try{
            User user = userRepository.getById(id);
            user.setAccountEnabled(true);
            userRepository.save(user);
        }catch (Exception e){
            new ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Operation successfully!",HttpStatus.OK);
    }
}
