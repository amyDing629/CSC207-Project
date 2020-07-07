package Inventory;

import User.User;

/**
 * present information of the inventory system to user.
 */
public class InventoryPresenter {
    User currUser;
    Inventory iv = new Inventory();

    /**
     * [constructor]
     * @param currUser the user that is using the system
     */
    public InventoryPresenter(User currUser){
        this.currUser = currUser;
    }

    /**
     * print the item that is not in the trade.
     */
    public void printAvailable(){
        System.out.println(iv.getAvailableList());
    }

    /**
     * print name, description and owner of the item
     * @param item selected item
     */
    public void printItemInfo(Item item) {
        System.out.println("item name: " + item.getName());
        System.out.println("item description: " + item.getDescription());
        System.out.println("item owner: " + item.getOwnerName());
    }

}
