package main.java.repository;

import main.java.model.MenuItems;

import java.util.*;

public class MenuRepository {
    private List<MenuItems> lstItems = new ArrayList<>();

    public void addMenuItems(MenuItems items){
        lstItems.add(items);
    }


    public List<MenuItems> getListItems(int menuType){
        List<MenuItems> filterList = new ArrayList<>();
        if (menuType != 0) {
            for (MenuItems item : lstItems) {
                if (item.getMenuType() == menuType) {
                    filterList.add(item);
                }
            }
            filterList.sort(Comparator.comparingInt(MenuItems::getMenuType).thenComparingInt(MenuItems::getId));
            return new ArrayList<>(filterList);
        }
        else{
            lstItems.sort(Comparator.comparingInt(MenuItems::getMenuType).thenComparingInt(MenuItems::getId));
            return new ArrayList<>(lstItems);
        }
    }

    public MenuItems getItemById(int itemId){
        for (int i = 0; i < lstItems.size(); i++){
            if (lstItems.get(i).getId() == itemId){
                return lstItems.get(i);
            }
        }
        return null;
    }


    public void updateItem(MenuItems items){
        for (int i = 0; i < lstItems.size(); i++){
            if (lstItems.get(i).getId() == items.getId()){
                lstItems.set(i, items);
            }
        }
    }

    public void deleteItem(MenuItems items){
        lstItems.remove(items);
    }
}
