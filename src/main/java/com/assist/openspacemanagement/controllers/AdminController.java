package com.assist.openspacemanagement.controllers;

import com.assist.openspacemanagement.entities.BuildingEntity;
import com.assist.openspacemanagement.entities.UserEntity;
import com.assist.openspacemanagement.repositories.BuildingRepository;
import com.assist.openspacemanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    UserRepository userRepository;
    BuildingRepository buildingRepository;

    @Autowired
    public AdminController(UserRepository userRepository, BuildingRepository buildingRepository) {
        this.userRepository = userRepository;
        this.buildingRepository = buildingRepository;
    }

    // Usage: http://URL/admin/get-user-details?id=x
    @GetMapping(value = "/admin/get-user-details")
    public UserEntity getUserDetails(@RequestParam int id) {
        UserEntity user = userRepository.getById(id);
        return user;
    }

    @PostMapping(value = "/admin/add-building")
    public void addBuilding(@RequestBody BuildingEntity buildingEntity) {
        buildingRepository.save(buildingEntity);
    }

}
