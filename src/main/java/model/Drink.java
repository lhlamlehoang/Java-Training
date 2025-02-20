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

    @Override
    public void displayMenu(){
        System.out.println("1. Show Menu\n2. Add Menu\n3. Update Menu\n4. Delete Menu\n5. Order\n0. Exit");
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
