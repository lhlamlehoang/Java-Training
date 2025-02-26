package com.training.javatrainingphase2.service;


import com.training.javatrainingphase2.exception.MenuItemNotFoundException;
import com.training.javatrainingphase2.exception.UserUnauthorizedException;
import com.training.javatrainingphase2.model.Drink;
import com.training.javatrainingphase2.model.Food;
import com.training.javatrainingphase2.model.MenuItems;
import com.training.javatrainingphase2.repository.MenuRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MenuService {
    private static final Logger log = LoggerFactory.getLogger(MenuService.class);
    private final MenuRepository menuRepository;
    private final JwtService jwtService;

    public MenuService (MenuRepository menuRepository, JwtService jwtService){
        this.menuRepository = menuRepository;
        this.jwtService = jwtService;
    }

    public ResponseEntity<String> addItem(String token, MenuItems item){
        String role = jwtService.extractUserRole(token.substring(7));

        if (!role.equals("[ROLE_ADMIN]")){
            throw new UserUnauthorizedException("User unauthorized!");
        }

        menuRepository.save(item);
        return new ResponseEntity<>("Item added successfully!", HttpStatus.CREATED);
    }

    public Page<MenuItems> getAllItems (Pageable pageable){
        log.info("Get all item with pagination");
        return menuRepository.getAllItems(pageable);
    }

    public ResponseEntity<Object> getItemById(Long id){
        MenuItems menuItem = menuRepository.findById(id).orElseThrow(() -> new MenuItemNotFoundException("Menu item not found with id " + id));
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteItemById(Long id, String token){
        String role = jwtService.extractUserRole(token.substring(7));;

        if (!role.equals("[ROLE_ADMIN]")){
            throw new UserUnauthorizedException("User unauthorized!");
        }

        MenuItems menuItem = menuRepository.findById(id).orElseThrow(() -> new MenuItemNotFoundException("Menu item not found with id " + id));
        menuRepository.softDeleteItemById(id);
        return new ResponseEntity<>("Item has been deleted!", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateItem(MenuItems item, String token){
        String role = jwtService.extractUserRole(token.substring(7));;

        if (!role.equals("[ROLE_ADMIN]")){
            throw new UserUnauthorizedException("User unauthorized!");
        }

        MenuItems menuItem = menuRepository.findById(item.getId()).orElseThrow(() -> new MenuItemNotFoundException("Menu item not found with id " + item.getId()));
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
