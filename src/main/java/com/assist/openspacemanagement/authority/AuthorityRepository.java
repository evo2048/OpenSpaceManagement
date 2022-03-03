package com.assist.openspacemanagement.authority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    public Authority findAuthorityEntityByRole(String role);

}
