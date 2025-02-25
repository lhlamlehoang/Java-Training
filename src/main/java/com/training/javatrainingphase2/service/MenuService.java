package com.training.javatrainingphase2.service;


import com.training.javatrainingphase2.model.Drink;
import com.training.javatrainingphase2.model.Food;
import com.training.javatrainingphase2.model.MenuItems;
import com.training.javatrainingphase2.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final JwtService jwtService;
    private final UserInfoService userInfoService;

    public MenuService (MenuRepository menuRepository, JwtService jwtService, UserInfoService userInfoService){
        this.menuRepository = menuRepository;
        this.jwtService = jwtService;
        this.userInfoService = userInfoService;
    }

    public ResponseEntity<String> addItem(String token, MenuItems item){
        String role = jwtService.extractUserRole(token.substring(7));;

        if (!role.equals("ROLE_ADMIN")){
            return new ResponseEntity<>("User unauthorized!", HttpStatus.BAD_REQUEST);
        }

        menuRepository.save(item);
        return new ResponseEntity<>("Item added successfully!!", HttpStatus.OK);
    }

    public Page<MenuItems> getAllItems (Pageable pageable){
        return menuRepository.getAllItems(pageable);
    }

    public ResponseEntity<Object> getItemById(Long id, String token){
        Optional<MenuItems> menuItem = menuRepository.findById(id);
        if (menuItem.isEmpty()){
            return new ResponseEntity<>("Item id not exist!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(menuItem.get(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteItemById(Long id, String token){
        String role = jwtService.extractUserRole(token.substring(7));;

        if (!role.equals("ROLE_ADMIN")){
            return new ResponseEntity<>("User unauthorized!", HttpStatus.BAD_REQUEST);
        }

        Optional<MenuItems> menuItem = menuRepository.findById(id);
        if (menuItem.isEmpty()){
            return new ResponseEntity<>("Item id not exist!", HttpStatus.BAD_REQUEST);
        }
        menuRepository.softDeleteItemById(id);
        return new ResponseEntity<>("Item has been deleted!", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateItem(MenuItems item, String token){
        String role = jwtService.extractUserRole(token.substring(7));;

        if (!role.equals("ROLE_ADMIN")){
            return new ResponseEntity<>("User unauthorized!", HttpStatus.BAD_REQUEST);
        }

        Optional<MenuItems> menuItem = menuRepository.findById(item.getId());
        if (menuItem.isEmpty()){
            return new ResponseEntity<>("Item id not exist!", HttpStatus.BAD_REQUEST);
        }
        if (item instanceof Food){
            MenuItems newItem = new Food(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getNote(), item.getImg(), item.getStatus(), ((Food) item).getFoodType());
            menuRepository.save(newItem);
        }
        else if (item instanceof Drink){
            MenuItems newItem = new Drink(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getNote(), item.getImg(), item.getStatus(), ((Drink) item).getAlcoholic());
            menuRepository.save(newItem);
        }
        return new ResponseEntity<>("Item has been updated!", HttpStatus.OK);
    }

    public Page<MenuItems> findItem(String keyword, Pageable pageable){
        return menuRepository.findItem(keyword, pageable);
    }
}
