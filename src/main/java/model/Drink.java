package main.java.model;

public class Drink extends MenuItems {
    private String type;

    public Drink (){
        super();
    }

    public Drink(int id, String name, String description, double price, String type, int menuType){
        super(id, name, description, price, menuType);
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return "Drink: " + id + " - " + name  + " - " + description  + " - " + price  + " - " + type;
    }
}
