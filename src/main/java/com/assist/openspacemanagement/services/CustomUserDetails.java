package com.assist.openspacemanagement.services;

import com.assist.openspacemanagement.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    @Autowired
    private UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getAuthority()));
        return list;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !userEntity.isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() { return !userEntity.isAccountLocked(); }

    @Override
    public boolean isCredentialsNonExpired() {
        return !userEntity.isPasswordExpired();
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isAccountEnabled();
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
