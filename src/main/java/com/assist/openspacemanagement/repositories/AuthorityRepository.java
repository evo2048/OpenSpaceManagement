package com.assist.openspacemanagement.repositories;

import com.assist.openspacemanagement.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
    public AuthorityEntity findAuthorityEntityByRole(String role);

}
