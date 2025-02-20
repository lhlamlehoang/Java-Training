import model.*;
import service.BillService;
import service.MenuService;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Initialize data
        MenuItems coca = new Drink(1, "Coca", "Coca Cola", null, 20000, "Soft Drink", 1);
        MenuItems pepsi = new Drink(2, "Pepsi", "Pepsi Co", null, 20000, "Soft Drink", 1);
        MenuItems chicken = new Food(3, "Fried Chicken", "Fast food", null, 30000, "Lunch", 2);
        MenuItems Hamburger = new Food(4, "Hamburger", "Beef Hamburger", null, 25000, "Lunch", 2);

        MenuService menuService = new MenuService();
        menuService.addMenuItems(coca);
        menuService.addMenuItems(pepsi);
        menuService.addMenuItems(chicken);
        menuService.addMenuItems(Hamburger);

        BillService billService = new BillService();

        String filePath = "D:\\HL\\JavaTraining\\bill.txt";
















        // Read user input
        Scanner scanner = new Scanner(System.in);
        int opt = -1;

        while(opt != 0){
            System.out.println();
            coca.displayMenu();
            System.out.print("Input the number that you want to choose: ");
            opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt){
                case 1: // Show Menu
                    ListIterator<MenuItems> listIterator = menuService.getListItems(0).listIterator();
                    while(listIterator.hasNext()){
                        System.out.println(listIterator.next());
                    }
                    break;

                case 2: // Add item
                    System.out.println("1. Add Drink\n2. Add Food");
                    String optAdd = scanner.nextLine();

                    String MenuType = optAdd.equals("1") ? "Drink" : "Food";

                    System.out.print("Input " + MenuType + " ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Input " + MenuType + " name: ");
                    String name = scanner.nextLine();

                    System.out.print("Input " + MenuType + " description: ");
                    String description = scanner.nextLine();

                    System.out.print("Input " + MenuType + " price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Input " + MenuType + " type: ");
                    String type = scanner.nextLine();

                    if (optAdd.equals("1")){
                        MenuItems drink = new Drink(id, name, description, null, price, type, 1);
                        menuService.addMenuItems(drink);
                    }
                    else{
                        MenuItems food = new Food(id, name, description, null, price, type, 2);
                        menuService.addMenuItems(food);
                    }

                    System.out.println("Item has been added!");
                    break;

                case 3: // Update item
                    System.out.print("Input the ID of item you want to update: ");
                    int itemIdUpdate = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println(menuService.getItemById(itemIdUpdate));
                    while (menuService.getItemById(itemIdUpdate) == null){
                        System.out.println("Cannot find any item with this id, please input again or 0 to exit: ");
                        itemIdUpdate = scanner.nextInt();
                        scanner.nextLine();
                        if (itemIdUpdate == 0){
                            break;
                        }
                    }


                    if (menuService.getItemById(itemIdUpdate) != null){
                        System.out.print("Input item name: ");
                        String nameUpdate = scanner.nextLine();

                        System.out.print("Input item description: ");
                        String descriptionUpdate = scanner.nextLine();

                        System.out.print("Input item price: ");
                        double priceUpdate = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Input item type: ");
                        String typeUpdate = scanner.nextLine();

                        int menuType = menuService.getItemById(itemIdUpdate).getMenuType();
                        if (menuType == 1){
                            MenuItems itemUpdate = new Drink(itemIdUpdate, nameUpdate, descriptionUpdate, null, priceUpdate, typeUpdate, 1);
                            menuService.updateItem(itemUpdate);
                        }
                        else{
                            MenuItems itemUpdate = new Food(itemIdUpdate, nameUpdate, descriptionUpdate, null, priceUpdate, typeUpdate, 2);
                            menuService.updateItem(itemUpdate);
                        }
                    }

                    System.out.println("Item has been updated!");
                    break;

                case 4: // Delete item
                    System.out.print("Input the ID of item you want to delete: ");
                    int itemIdDelete = scanner.nextInt();
                    scanner.nextLine();

                    while (menuService.getItemById(itemIdDelete) == null){
                        System.out.println("Cannot find any item with this id, please input again or 0 to exit: ");
                        itemIdDelete = scanner.nextInt();
                        scanner.nextLine();
                        if (itemIdDelete == 0){
                            break;
                        }
                    }

                    if (menuService.getItemById(itemIdDelete) != null) {
                        menuService.deleteItem(menuService.getItemById(itemIdDelete));
                    }
                    System.out.println("Item has been deleted!");

                    break;

                case 5:
                    Map<Integer, BillItems> billItemsList = new HashMap<>();
                    ListIterator<MenuItems> itemsIterator = menuService.getListItems(0).listIterator();
                    while(itemsIterator.hasNext()){
                        System.out.println(itemsIterator.next());
                    }

                    while (true){
                        System.out.print("ID of menu item you want to choose (or press 0 to exit): ");
                        int selectedId = scanner.nextInt();
                        scanner.nextLine();

                        // Exit select if select 0
                        if (selectedId == 0){
                            break;
                        }

                        System.out.print("Input quantity: ");
                        int selectedQty = scanner.nextInt();
                        scanner.nextLine();

                        // Add item into bill
                        BillItems newBillItem = new BillItems(menuService.getItemById(selectedId), selectedQty);

                        BillItems currentItem = billItemsList.get(selectedId);
                        if (billItemsList.containsKey(selectedId)){
                            currentItem.setQuantity(currentItem.getQuantity() + selectedQty);
                        }
                        else{
                            billItemsList.put(selectedId, newBillItem);
                        }
                    }


                    if (!billItemsList.isEmpty()){
                        // Print total bill
                        System.out.println("\nYour bill: ");
                        Bill bill = new Bill(billService.getListBill().size(), billItemsList.values().stream().toList(), LocalDateTime.now());
                        System.out.println(bill);
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice, please input again!");


            }
        }




        // Update drink
//        Drink cocaUpdate = new Drink(1, "Coca Cola", "Soft Drink", null, 25000, "Soft Drink");
//        menuService.updateDrink(cocaUpdate);
//        // Delete drink
//        menuService.deleteDrink(tiger);




    }
}