package com.training.JavaTrainingPhase2.service;


import com.mysql.cj.x.protobuf.Mysqlx;
import com.training.JavaTrainingPhase2.model.MenuItems;
import com.training.JavaTrainingPhase2.model.UserInfo;
import com.training.JavaTrainingPhase2.repository.MenuRepository;
import com.training.JavaTrainingPhase2.repository.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final JwtService jwtService;
    private final UserInfoRepository userInfoRepository;

    public MenuService (MenuRepository menuRepository, JwtService jwtService, UserInfoRepository userInfoRepository){
        this.menuRepository = menuRepository;
        this.jwtService = jwtService;
        this.userInfoRepository = userInfoRepository;
    }

    public ResponseEntity<String> addItem(String token, MenuItems item){
        String username = jwtService.extractUsername(token.substring(7));
        Optional<UserInfo> userInfo = userInfoRepository.getByEmail(username);
        String role = "";
        if (userInfo.isPresent()){
            role = userInfo.get().getRoles();
        }
        else{
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }

        if (role.equals("ROLE_USER")){
            return new ResponseEntity<>("User unauthorized!", HttpStatus.BAD_REQUEST);
        }

        menuRepository.save(item);
        return new ResponseEntity<>("Item added successfully!!", HttpStatus.OK);
    }

    public ResponseEntity<List<MenuItems>> getListItems (){
        return new ResponseEntity<>(menuRepository.findAll(), HttpStatus.OK);
    }
}
