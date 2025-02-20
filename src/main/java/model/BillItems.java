package main.java.model;

public class BillItems {
    private int id;
    private MenuItems menuItems;
    private int quantity;

    public BillItems (){

    }

    public BillItems(int id, MenuItems menuItems, int quantity){
        this.id = id;
        this.menuItems = menuItems;
        this.quantity = quantity;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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
