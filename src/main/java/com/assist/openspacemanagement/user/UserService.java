package com.assist.openspacemanagement.user;

import com.assist.openspacemanagement.utils.Diverse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{


    public static UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
    public ResponseEntity<String> serviceDeactivateUser(int id) {
        try{
            User user = userRepository.getById(id);
            if (!user.getAuthority().getRole().equals("ADMIN")) {
                user.setAccountEnabled(false);
                userRepository.save(user);
            }else{
                return new ResponseEntity<>("You are ADMIN",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            new ResponseEntity<>("An error occurred",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Operation successfully!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> serviceActivatedUser(int id) {
        try{
            User user = userRepository.getById(id);
            user.setAccountEnabled(true);
            userRepository.save(user);
        }catch (Exception e){
            new ResponseEntity<String>("An error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Operation successfully!",HttpStatus.OK);
    }

    @Override
    public List<JSONObject> getStatusUserForEmployee(String filterByWord) {
        List<JSONObject> lstUser = new ArrayList<>();
        try{
            List<User> lst = userRepository.findByWord(filterByWord);
            lst.forEach(user -> {
                 JSONObject obj = Diverse.userToStatus(user);
                 lstUser.add(obj);
            });
        }catch (Exception e){
            return null;
        }
        return lstUser;
    }

    @Override
    public List<User> getStatusAllUser(String filterByWord) {
        List<User> lstUsers;
        try{
            lstUsers = userRepository.findByWord(filterByWord);
        }catch (Exception e){
            return null;
        }
        return lstUsers;
    }
}
