package service;

import model.Drink;
import model.Food;
import model.MenuItems;
import repository.MenuRepository;

import java.util.List;


public class MenuService {
    private final MenuRepository menuRepository = new MenuRepository();

    public void addMenuItems(MenuItems items){
        menuRepository.addMenuItems(items);
    }

    public void updateItem(MenuItems items){
        menuRepository.updateItem(items);
    }

    public void deleteItem(MenuItems items){
        menuRepository.deleteItem(items);
    }

    public List<MenuItems> getListItems(int menuType){
        return menuRepository.getListItems(menuType);
    }

    public MenuItems getItemById(int itemId){
        return menuRepository.getItemById(itemId);
    }

}
