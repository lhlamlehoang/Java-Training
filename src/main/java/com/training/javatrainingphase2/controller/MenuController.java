package com.training.javatrainingphase2.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.javatrainingphase2.model.MenuItems;
import com.training.javatrainingphase2.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController (MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/getAll")
    public Map<String, Object> getAllItems(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<MenuItems> p = menuService.getAllItems(PageRequest.of(page, size));
        return Map.of(
                "page", p.getNumber(),
                "items", p.getContent(),
                "totalElements", p.getTotalElements(),
                "totalPages", p.getTotalPages(),
                "currentElements", p.getNumberOfElements()
        );
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getItemById(@RequestParam("id") Long id, @RequestHeader("Authorization") String token){
        return menuService.getItemById(id, token);
    }

    @PostMapping(value = "/addItem", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addItem (@RequestHeader("Authorization") String token,
                                           @RequestPart("itemJson") String itemJson,
                                           @RequestPart("image") MultipartFile img){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MenuItems item = objectMapper.readValue(itemJson, MenuItems.class);
            byte[] imgByte = img.getBytes();
            item.setImg(imgByte);
            return menuService.addItem(token, item);
        }
        catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>("Error during upload image!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteItem(@RequestParam("id") Long id, @RequestHeader("Authorization") String token){
        return menuService.deleteItemById(id, token);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateItem(@RequestPart("itemJson") String itemJson,
                                             @RequestPart("image") MultipartFile img,
                                             @RequestHeader("Authorization") String token) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            MenuItems item = objectMapper.readValue(itemJson, MenuItems.class);
            item.setImg(img.getBytes());
            return menuService.updateItem(item, token);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error during upload image!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findItem")
    public Map<String, Object> findItem(@RequestParam("keyword") String keyword,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size){
        Page<MenuItems> p = menuService.findItem(keyword, PageRequest.of(page, size));
        return Map.of(
                "page", p.getNumber(),
                "items", p.getContent(),
                "totalElements", p.getTotalElements(),
                "totalPages", p.getTotalPages(),
                "currentElements", p.getNumberOfElements()
        );
    }
}
