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
    private final Inventory iv;
    /**
     * the user that is using the system.
     */
    private final User currUser;

    /**
     * [constructor]
     * @param currUser current user
     */
    InventoryController(User currUser, Inventory iv){
        this.currUser = currUser;
        this.iv = iv;
    }

    /**
     * user select an item and record the item in the system
     * @param line the input from user of the item selected.
     * @return wheter the item has been selected.
     */
    boolean selectItem(String line){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(line)){
                /**
                 * the item that the user selects. item equals to null if the user hasn't selected an item.
                 */
                System.out.println(it + " has been selected");
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
    boolean isOwnItem(Item it){
        return it.getOwnerName().equals(currUser.getUsername());
    }

    /**
     * move the selected item to user's wishBorrow list.
     * @param it the selected item.
     */
    void moveToWishList(Item it){
        currUser.addWishBorrow(it.getName());
    }

    boolean isInOwnWishList(Item it){
        return currUser.getWishBorrow().contains(it.getName());
    }
}
