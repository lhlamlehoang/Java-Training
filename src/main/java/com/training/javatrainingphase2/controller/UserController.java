package com.training.javatrainingphase2.controller;

import com.training.javatrainingphase2.model.UserInfo;
import com.training.javatrainingphase2.security.AuthRequest;
import com.training.javatrainingphase2.service.JwtService;
import com.training.javatrainingphase2.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "User Controller", description = "Manage user, auth")
public class UserController {
    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserInfoService userInfoService, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Operation(description = "Add new user (no need auth)")
    @PostMapping("/addNewUser")
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }


    @Operation(description = "Generate JWT token for authentication")
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            System.out.println("Authorities: " + authentication.getAuthorities());
            return jwtService.generateToken(authRequest.getUsername(), authentication.getAuthorities().toString());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}


