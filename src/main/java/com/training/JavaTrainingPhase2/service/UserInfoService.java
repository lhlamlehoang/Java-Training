package com.training.JavaTrainingPhase2.service;

import com.training.JavaTrainingPhase2.model.UserInfo;
import com.training.JavaTrainingPhase2.repository.UserInfoRepository;
import com.training.JavaTrainingPhase2.security.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoService(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<UserInfo> userInfo = userInfoRepository.getByEmail(username);

        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with user name + " + username + " not found!"));
    }

    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added successfully!";
    }
}
