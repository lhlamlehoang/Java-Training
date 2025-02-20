package model;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;

public class BillItems {
    private MenuItems menuItems;
    private int quantity;

    public BillItems(MenuItems menuItems, int quantity){
        this.menuItems = menuItems;
        this.quantity = quantity;
    }

    // Getters and Setters
    public MenuItems getMenuItems(){
        return menuItems;
    }

    public void setMenuItems (MenuItems menuItems){
        this.menuItems = menuItems;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
