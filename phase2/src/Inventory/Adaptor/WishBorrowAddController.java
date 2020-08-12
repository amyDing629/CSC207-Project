package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUI;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

public class WishBorrowAddController implements iItemController {
    /**
     * the inventory of the system.
     */
    private final Inventory iv;
    /**
     * the user that is using the system.
     */
    private final UUID currUser;

    UserManager um;

    BorderGUI bta;

    String it;

    iItemPresenter ip;

    JFrame fr;

    /**
     * [constructor]
     * @param currUser current user
     */
    public WishBorrowAddController(String currUser, BorderGUI bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }

    void moveToWishList(){
        ClientUser user = um.popUser(currUser);
        user.getWishBorrow().add(it);
        um.addUser(user);
    }

    public boolean isInList(String name){
        for (String it: um.getWishBorrow(currUser)){
            if (it.equals(name)){
                return true;
            }
        }
        return false;
    }

    public void updateCurr(){
        ip.resetCurr();
    }


    public void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    public String getItemInfo(Item it) {
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
    }

    private boolean isOwnItem(){
        return iv.getOwnerUUID(it).equals(currUser);
    }

    private boolean noItemFound(){
        return it == null;
    }

    public void updateBut(){
        updateList();
        updateCurr();
    }

    @Override
    public void performActionOne() {
        addToWishBorrow();
    }

    @Override
    public void performActionTwo() {
    }

    @Override
    public void performActionThree() {
    }


    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!iv.getAvailableList().contains(input)){
            ip.wrongInput();
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    public String printList(){
        String result = "";
        for (String it: iv.getAvailableList()){
            result = result + it + "\n";
        }
        if (result.equals("")){
            return "no available item";
        }
        return result;

    }


    public void updateList(){
        ip.updateList(printList());
    }

    private void addToWishBorrow(){
        if (noItemFound() || isOwnItem()) {
            ip.addToWishBorrow(false);
        } else if (isInList(it)) {
            ip.isInWishBorrow();
        } else {
            moveToWishList();
            ip.updateList(printList());
            ip.addToWishBorrow(true);
        }
    }
}

