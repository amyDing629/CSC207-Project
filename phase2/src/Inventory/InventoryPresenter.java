package Inventory;

import User.ClientUser;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * present information of the inventory system to user.
 */
public class InventoryPresenter {
    ClientUser currUser;
    Inventory iv;

    /**
     * [constructor]
     * @param currUser the user that is using the system
     */
    InventoryPresenter(ClientUser currUser, Inventory iv){
        this.currUser = currUser;
        this.iv = iv;
    }

    /**
     * print the item that is not in the trade.
     * @return the item name in 'item1,item2' format
     */
    String printAvailable(){
        String result = "";
        for (Item it: iv.getAvailableList()){
            result = result + iv.getName(it) + "\n";
        }
        if (result.equals("")){
            return "no available item";
        }
        return result;

    }



    /**
     * print name, description and owner of the item
     * @param item selected item
     */
    String printItemInfo(Item item) {
        /*
        System.out.println("item name: " + item.getName());
        System.out.println("item description: " + item.getDescription());
        System.out.println("item owner: " + item.getOwnerName());

         */
        return "Item Info:\nitem name: " + iv.getName(item) + "\n" +
                "item description: " + iv.getDescription(item)
                + "\n" + "item owner: " + iv.getOwnerName(item) ;
    }

    /**
     * print the message
     */
    String wrongInput(){
        return "wrong input, please type again";
    }

    /**
     * print the guidance
     */
    void selectItem(){
        System.out.println("type '1' to exit, or select an item");
    }

    /**
     * print the menu.
     */
    void itemAction(){
        System.out.println("menu:\n type '1' to add to wish borrow list and return back to the inventory" +
                "\n type '2' to return back to inventory directly");
    }

    /**
     * print the notification
     */
    String addToWishBorrow(boolean isAdded){
        if (isAdded){
            return "the item has been moved to the wish list";
        }else{
            return "you can not add your own item to wish borrow list";
        }
    }

    /**
     * print the message
     */
    String isInWishBorrow(){
        return "the item has already been in your wish list";
    }





}
