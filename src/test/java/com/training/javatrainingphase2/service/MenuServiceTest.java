package com.training.javatrainingphase2.service;

import com.training.javatrainingphase2.exception.MenuItemNotFoundException;
import com.training.javatrainingphase2.model.Drink;
import com.training.javatrainingphase2.model.Food;
import com.training.javatrainingphase2.model.MenuItems;
import com.training.javatrainingphase2.repository.MenuRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @Mock
    MenuRepository menuRepository;

    @Mock
    JwtService jwtService;

    @InjectMocks
    MenuService menuService;
    private static final String ADMIN_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IltST0xFX0FETUlOXSIsInN1YiI6ImxhbUBlbWFpbCIsImlhdCI6MTc0MDUzNzU4NSwiZXhwIjoxNzQwNjIzOTg1fQ.bP97hhQfmjBwHfEQ4XstDI4NXckRInvBW7cX0hcL1Ic";

    @Test
    void getItemById_ItemExists_ReturnsItem(){
        Long id = 1L;
        MenuItems mockItem = new Food(id, "Pizza", "Pizza Company", 100000, "", null, 1, 2);
        when(menuRepository.findById(id)).thenReturn(Optional.of(mockItem));

        ResponseEntity<Object> result = menuService.getItemById(id);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, ((MenuItems) result.getBody()).getId());
    }

    @Test
    void getItemById_ItemNotFound_ThrowsException(){
        Long id = 1L;
        when(menuRepository.findById(id)).thenReturn(Optional.empty());

        // Assert
        Assertions.assertThrows(MenuItemNotFoundException.class, () -> menuService.getItemById(id));
    }

    @Test
    void deleteItem_ItemDeleted(){
        MenuItems item = new Food(1L, "Pizza", "Pizza Company", 100000, "", null, 1, 2);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("Item has been deleted!", HttpStatus.OK);
        when(jwtService.extractUserRole(ADMIN_TOKEN.substring(7))).thenReturn("[ROLE_ADMIN]");
        when(menuRepository.findById(item.getId())).thenReturn(Optional.of(item));
        doNothing().when(menuRepository).softDeleteItemById(item.getId());

        ResponseEntity<String> actualResult = menuService.deleteItemById(item.getId(), ADMIN_TOKEN);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult);
        verify(menuRepository, times(1)).findById(item.getId());
        verify(menuRepository, times(1)).softDeleteItemById(item.getId());
    }
}
