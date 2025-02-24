package com.training.JavaTrainingPhase2.controller;


import com.training.JavaTrainingPhase2.model.MenuItems;
import com.training.JavaTrainingPhase2.service.MenuService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController (MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/getListItems")
    public ResponseEntity<List<MenuItems>> getListItems(){
        return menuService.getListItems();
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem (@RequestHeader("Authorization") String token, @RequestBody MenuItems item){
        return menuService.addItem(token, item);
    }
}
