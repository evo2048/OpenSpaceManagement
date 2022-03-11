package com.assist.openspacemanagement.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {
    // DO NOT USE WITHOUT USER INPUT SANITIZATION, SQL INJECTION RISK
    @Query(value="select * from users where email = :email", nativeQuery = true)
    User findByEmail(String email);

    //get all user contains substring in full name
    @Query(value = "select * from users where frist_name like %:word% or last_name like %:word%",nativeQuery = true)
    List<User> findByWord(String word);
}
