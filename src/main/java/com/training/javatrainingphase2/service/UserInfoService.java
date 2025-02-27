package com.training.javatrainingphase2.service;

import com.training.javatrainingphase2.model.UserInfo;
import com.training.javatrainingphase2.repository.UserInfoRepository;
import com.training.javatrainingphase2.security.UserInfoDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    public ResponseEntity<String> addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return new ResponseEntity<>("User added successfully!", HttpStatus.CREATED);
    }
}
