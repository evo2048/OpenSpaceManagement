package com.assist.openspacemanagement.repositories;

import com.assist.openspacemanagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // DO NOT USE WITHOUT USER INPUT SANITIZATION, SQL INJECTION RISK
    @Query(value="select * from users where email = :email", nativeQuery = true)
    public UserEntity findByEmail(String email);
}
