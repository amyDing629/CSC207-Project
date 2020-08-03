package Inventory;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import java.util.ArrayList;
import java.util.List;

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
    private final ClientUser currUser;

    UserManager um;

    ItemApprovalManager iam;

    /**
     * [constructor]
     * @param currUser current user
     */
    public InventoryController(ClientUser currUser, Inventory iv, UserManager um, ItemApprovalManager iam){
        this.currUser = currUser;
        this.iv = iv;
        this.um = um;
        this.iam = iam;
    }

    /**
     * user select an item and record the item in the system
     * @param line the input from user of the item selected.
     * @return wheter the item has been selected.
     */
    boolean selectItem(String line){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(line)){
                //System.out.println(it + " has been selected");
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

    void addToWishLend(Item it){
        um.getWishLend(um.getUser(it.getOwnerName())).add(it.getName());
        iv.getLendingList().add(it);
        iam.removeItem(it.getName());
    }

    void removeItemFromIam(Item it){
        iam.removeItem(it.getName());
    }

    /**
     * @param it: current item
     * @return whether the item is the currUser's wish list
     */
    boolean isInOwnWishList(Item it){
        return currUser.getWishBorrow().contains(it.getName());
    }

    /**
     * @param line item name
     * @return item
     */
    Item getItem(String line){
        return iv.getItem(line);
    }

    public String printWishLend(){
        StringBuilder result = new StringBuilder();
        for (String it: um.getWishLend(currUser)){
            result.append(it).append("\n");
        }
        if (result.toString().equals("")){
            return "no item";
        }
        return result.toString();
    }

    public String printWishBorrow(){
        StringBuilder result = new StringBuilder();
        for (String it: um.getWishBorrow(currUser)){
            result.append(it).append("\n");
        }
        if (result.toString().equals("")){
            return "no item";
        }
        return result.toString();

    }


    public String printRequest() {
        ArrayList<ArrayList<String>> ia = iam.getItemApproval();
        String result = "";
        for (int i = 0; i < ia.size(); i++) {
            String str = "";
            str = str + "\n" + "name: " + ia.get(i).get(1) + "\n" + "Description: " + ia.get(i).get(2)
                    + "\n" + "Owner: " + ia.get(i).get(3) + "\n";
            result = result + str;
        }
        if (result.equals("")) {
            return "no requested items";
        }
        return result;

    }

    public boolean iamCheckInput(String name){
        ArrayList<ArrayList<String>> ia = iam.getItemApproval();
        for (ArrayList<String> strings : ia) {
            if (strings.get(1).equals(name)) {
                return true;
            }
        }
        return false;

    }

    public Item getItemFromIam(String name){
        Item result;
        ArrayList<ArrayList<String>> ia = iam.getItemApproval();
        for (ArrayList<String> strings : ia) {
            if (strings.get(1).equals(name)) {
                result = new Item(strings.get(1), strings.get(3));
                iv.setDescription(strings.get(2), result);
                return result;
            }
        }
        return null;

    }

    public List<String> getWishLend(){
        return um.getWishLend(currUser);
    }

    public List<String> getWishBorrow(){
        return um.getWishBorrow(currUser);
    }

    public void deleteItemL(Item it){
        um.getWishLend(currUser).remove(it.getName());
    }

    public void deleteItemB(Item it){um.getWishBorrow(currUser).remove(it.getName());}

    public void addItem(String name, String des){
        ArrayList<String> b= new ArrayList<>();
        b.add("1");
        b.add(name);
        b.add(des);
        b.add(um.getUsername(currUser));
        iam.getItemApproval().add(b);
    }

    Item createItem(String name){
        return iv.createItem(name, um.getUsername(currUser));
    }

    public void setDescription(String des, Item item){
        iv.setDescription(des, item);
    }

    boolean itemExist(String name){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(name)){
                return true;
            }
        }
        return false;
    }



}
