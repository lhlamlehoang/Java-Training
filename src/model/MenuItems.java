package model;

import java.awt.*;
import java.io.*;
import java.util.Comparator;

public abstract class MenuItems {
    protected int id;
    protected String name;
    protected String description;
    protected Image img;
    protected double price;
    protected int menuType;

    public MenuItems(int id, String name, String description, Image img, double price, int menuType){
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.price = price;
        this.menuType = menuType; // 1: Drink, 2: Food
    }

    public abstract void displayMenu();

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Image getImage(){
        return img;
    }

    public void setImage(Image img){
        this.img = img;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getMenuType(){
        return menuType;
    }

    public void setMenuType(int menuType){
        this.menuType = menuType;
    }
}
