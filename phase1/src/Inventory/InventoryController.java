package Inventory;
import User.User;

/**
 * [controller]
 * call use case class methods. Make edition to inventory system based on user's requests.
 */
public class InventoryController {
    /**
     * the inventory of the system.
     */
    Inventory iv = new Inventory();
    /**
     * the user that is using the system.
     */
    User currUser;
    /**
     * the item that the user selects. item equals to null if the user hasn't selected an item.
     */
    Item item;

    /**
     * [constructor]
     * @param currUser current user
     */
    public InventoryController(User currUser){
        this.currUser = currUser;
    }

    /**
     * user select an item and record the item in the system
     * @param line the input from user of the item selected.
     * @return wheter the item has been selected.
     */
    public boolean selectItem(String line){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(line)){
                item = it;
                System.out.println(item + " has been selected");
                return true;
            }
        }
        return false;
    }

    /**
     * if the input item is the user's own item, return true. Else, return false.
     * @param it the input item
     * @return whether the input item is the user's own item.
     */
    public boolean isOwnItem(Item it){
        if (it.getOwnerName().equals(currUser.getUsername())){
            return true;
        }
        return false;
    }

    /**
     * move the selected item to user's wishBorrow list.
     * @param it the selected item.
     */
    public void moveToWishList(Item it){
        currUser.addWishBorrow(it.getName());
    }
}
