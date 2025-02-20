package model;

import java.awt.*;

public class Food extends MenuItems {
    private String mealType;

    public Food (int id, String name, String description, Image img, double price, String mealType, int menuType){
        super(id, name, description, img, price, menuType);
        this.mealType = mealType;
    }

    @Override
    public void displayMenu(){
        System.out.println("1. Show Menu \n 2. Add Menu \n 3. Update Menu \n 4. Delete Menu");
    }

    public String getMealType(){
        return mealType;
    }

    public void setMealType(String mealType){
        this.mealType = mealType;
    }

    @Override
    public String toString(){
        return "Food: " + id + " - " + name  + " - " + description  + " - " + price  + " - " + mealType;
    }
}
