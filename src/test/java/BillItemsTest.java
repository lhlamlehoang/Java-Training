package test.java;


import main.java.model.Bill;
import main.java.model.BillItems;
import main.java.model.Food;
import main.java.model.MenuItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BillItemsTest {

    @Test
    void checkTotalAmountCorrect (){
        MenuItems hamburger = new Food(4, "Hamburger", "Beef Hamburger", 25000, "Lunch", 2);
        BillItems billItems = new BillItems();
        billItems.setMenuItems(hamburger);
        billItems.setQuantity(2);

        Assertions.assertEquals(50000, billItems.getMenuItems().getPrice() * billItems.getQuantity(), "The total amount should be > 0");
    }

    @Test
    void checkIdMoreThan0 (){
        BillItems billItems = new BillItems();
        billItems.setId(2);
        Assertions.assertTrue(billItems.getId() > 0, "The ID of item should be > 0");
    }

    @Test
    void CheckQuantityMoreThan0 (){
        BillItems billItems = new BillItems();
        billItems.setQuantity(5);
        Assertions.assertTrue(billItems.getQuantity() > 0, "The quantity of item should be > 0");
    }

    @Test
    void CheckBillItemsNull (){
        MenuItems food = new Food();
        food.setId(1);

        BillItems billItems = new BillItems();
        billItems.setId(2);
        billItems.setMenuItems(food);

        List<BillItems> billItemsList = new ArrayList<>();
        billItemsList.add(billItems);

        Bill bill = new Bill();
        bill.setId(3);
        bill.setLstBillItems(billItemsList);

        Assertions.assertTrue(!bill.getLstBillItems().isEmpty(), "The bill items list size should not be empty");
    }
}
