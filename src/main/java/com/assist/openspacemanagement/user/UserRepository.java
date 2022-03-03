package com.assist.openspacemanagement.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    // DO NOT USE WITHOUT USER INPUT SANITIZATION, SQL INJECTION RISK
    @Query(value="select * from users where email = :email", nativeQuery = true)
    public User findByEmail(String email);
}
