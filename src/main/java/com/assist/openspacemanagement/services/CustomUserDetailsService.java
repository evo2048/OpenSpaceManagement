package com.assist.openspacemanagement.services;

import com.assist.openspacemanagement.entities.UserEntity;
import com.assist.openspacemanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        CustomUserDetails customUserDetails;
        if(user != null) {
            customUserDetails = new CustomUserDetails();
            customUserDetails.setUserEntity(user);
        } else {
            throw new UsernameNotFoundException("User with email " + email + " does not exist.");
        }
        return customUserDetails;
    }
}
