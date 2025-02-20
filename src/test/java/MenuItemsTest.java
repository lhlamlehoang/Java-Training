package test.java;

import main.java.model.Drink;
import main.java.model.Food;
import main.java.model.MenuItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenuItemsTest {

    @Test
    void CheckPriceMoreThan0 (){
        MenuItems drink = new Drink();
        drink.setPrice(10000);

        MenuItems food = new Food();
        food.setPrice(20000);

        Assertions.assertTrue(drink.getPrice() > 0, "Item price shoud be > 0");
        Assertions.assertTrue(food.getPrice() > 0, "Item price shoud be > 0");
    }

    @Test
    void checkIdMoreThan0 (){
        MenuItems food = new Food();
        food.setId(1);

        MenuItems drink = new Drink();
        drink.setId(2);

        Assertions.assertTrue(food.getId() > 0, "Food ID should be > 0");

        Assertions.assertTrue(drink.getId() > 0, "Drink ID should be > 0");
    }
}