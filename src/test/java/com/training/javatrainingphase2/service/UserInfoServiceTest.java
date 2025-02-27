package com.training.javatrainingphase2.service;

import com.training.javatrainingphase2.model.UserInfo;
import com.training.javatrainingphase2.repository.UserInfoRepository;
import com.training.javatrainingphase2.security.UserInfoDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTest {
    @InjectMocks
    UserInfoService userInfoService;

    @Mock
    UserInfoRepository userInfoRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserInfoDetails userInfoDetails;

    @Test
    void addUser_UserAdded(){
        UserInfo user = new UserInfo(1, "name", "email", "password", "ROLE_ADMIN");
        when(userInfoRepository.save(any(UserInfo.class))).thenReturn(user);

        ResponseEntity<String> actualResult = userInfoService.addUser(user);

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
        verify(userInfoRepository, times(1)).save(any(UserInfo.class));
    }

    @Test
    void loadUserByUsername_ReturnUser(){
        UserInfo user = new UserInfo(1, "name", "email", "password", "ROLE_ADMIN");
        when(userInfoRepository.getByEmail(user.getEmail())).thenReturn(Optional.of(user));
        UserDetails userDetails = userInfoService.loadUserByUsername(user.getEmail());

        // Assert
        Assertions.assertEquals(user.getEmail(), userDetails.getUsername());
        verify(userInfoRepository, times(1)).getByEmail(user.getEmail());
    }

    @Test
    void findUserByEmail_UserEmailNotFound_ThrowsException(){
        String email = "email";
        when(userInfoRepository.getByEmail(email)).thenReturn(Optional.empty());

        // Assert
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userInfoService.loadUserByUsername(email));
    }
}
