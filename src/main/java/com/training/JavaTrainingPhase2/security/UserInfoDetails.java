package com.training.JavaTrainingPhase2.security;

import com.training.JavaTrainingPhase2.model.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(UserInfo userInfo){
        this.username = userInfo.getName();
        this.password = userInfo.getPassword();
        this.authorities = List.of(userInfo.getRoles().split(","))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }
}
