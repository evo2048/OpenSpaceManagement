package com.assist.openspacemanagement.repositories;

import com.assist.openspacemanagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // DO NOT USE WITHOUT USER INPUT SANITIZATION, SQL INJECTION RISK
    public UserEntity findByEmail(String email);
//    public UserEntity findByUsername(String username);
}
