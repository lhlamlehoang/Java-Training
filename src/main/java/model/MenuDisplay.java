package main.java.model;

public class MenuDisplay implements MenuInterface{

    public MenuDisplay(){}

    @Override
    public void displayMenu() {
        System.out.println("*********************************\n" +
                "1. Show Menu\n" +
                "2. Add Menu\n" +
                "3. Update Menu\n" +
                "4. Delete Menu\n" +
                "5. Choose menu\n" +
                "6. Print bill from file\n" +
                "0. Exit\n" +
                "*********************************");
    }
}
