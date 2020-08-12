package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIWithThreeTextArea;
import Trade.Adaptor.InputAndPresent;
import User.UseCase.UserManager;

import java.util.UUID;

/**
 * present information of the inventory system to user.
 */
public class InventoryPresenter implements iItemPresenter {
    InputAndPresent bta;

    /**
     * [constructor]
     */
    InventoryPresenter(BorderGUIWithThreeTextArea bta){
        this.bta = bta;
    }

    /**
     * print the message
     */
    public void wrongInput(){ bta.setMsgText("wrong input");
    }

    /**
     * print the notification
     */
    public void addToWishBorrow(boolean isAdded){
        if (isAdded){
            bta.setMsgText("the item has been moved to the wish list");
        }else{
            bta.setMsgText("you can not add your own item to wish borrow list");
        }
    }

    /**
     * print the message
     */
    public void isInWishBorrow(){
       bta.setMsgText("the item has already been in your wish list");
    }

    public void noItemSelected(){
        bta.setMsgText("no item has been selected");
    }

    public void resetCurr(){
        bta.setCurrText("no item selected");
    }

    public void updateCurr(String itemInfo){
        bta.setCurrText(itemInfo);
    }

    public void delSuccess(String itemName){
        bta.setMsgText(itemName + " has been deleted");
    }

    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }

    public void resetInputArea(){
        bta.setInput("input","item name");
    }

    public void voidItem(){
        bta.setMsgText("please input item name");
    }

    public void nameUsed(){
        bta.setMsgText("the item name has already been used");
    }

    public void requestSuccess(String itemName){
        bta.setMsgText(itemName + " has been requested, please wait for admin user to agree");
    }

    public void editDesSuccess(String itemName){
        bta.setMsgText(itemName + "'s description has been changed");
    }

    public void updateList(String availableList){
        bta.setListText(availableList);
    }

    public void addLSuccess(){
        bta.setMsgText("the item has been added to lend list successfully");
    }

    public void itemInInv(){
        bta.setMsgText("can't add the item since the item name already exist");
    }

}
