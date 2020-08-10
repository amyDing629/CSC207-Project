package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIWithThreeTextArea;
import Trade.Adaptor.InputAndPresent;
import User.UseCase.UserManager;

import java.util.UUID;

/**
 * present information of the inventory system to user.
 */
public class InventoryPresenter {
    InputAndPresent bta;

    /**
     * [constructor]
     */
    InventoryPresenter(BorderGUIWithThreeTextArea bta){
        this.bta = bta;
    }

    /**
     * print the item that is not in the trade.
     * @return the item name in 'item1,item2' format
     */



    /**
     * print the message
     */
    void wrongInput(){ bta.setMsgText("wrong input");
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
    void addToWishBorrow(boolean isAdded){
        if (isAdded){
            bta.setMsgText("the item has been moved to the wish list");
        }else{
            bta.setMsgText("you can not add your own item to wish borrow list");
        }
    }

    /**
     * print the message
     */
    void isInWishBorrow(){
       bta.setMsgText("the item has already been in your wish list");
    }

    void noItemSelected(){
        bta.setMsgText("no item has been selected");
    }

    void resetCurr(){
        bta.setCurrText("not item selected");
    }
    void updateCurr(String itemInfo){

        bta.setCurrText(itemInfo);
    }

    void delSuccess(String itemName){
        bta.setMsgText(itemName + " has been deleted");
    }

    void updateListB(UUID currUser){
        UserManager um = new UserManager();
        String result = "";
        for (String it: um.getWishBorrow(currUser)){
            result = result + it + "\n";
        }
        if (result.equals("")){
            bta.setListText("no item");
        }else{
            bta.setListText(result);
        }

    }

    void updateListL(UUID currUser){
        String result = "";
        UserManager um = new UserManager();
        for (String it: um.getUser(currUser).getWishLend()){
            result = result + it + "\n";
        }
        if (result.equals("")){
            bta.setListText("no item");
        }else{
            bta.setListText(result);
        }

    }

    void closeFrame(){
        bta.getFrame().setVisible(false);
    }

    void resetInputArea(){
        bta.setInput("input","item name");
    }

    void voidItem(){
        bta.setMsgText("please input item name");
    }

    void nameUsed(){
        bta.setMsgText("the item name has already been used");
    }

    void createSuccess(String itemName){
        bta.setMsgText(itemName + " has been created");
    }

    void requestSuccess(String itemName){
        bta.setMsgText(itemName + " has been requested, please wait for admin user to agree");
    }

    void editDesSuccess(String itemName){
        bta.setMsgText(itemName + "'s description has been changed");
    }

    void updateListM(String availableList){
        bta.setListText(availableList);
    }

    void addLSuccess(){
        bta.setMsgText("the item has been added to lend list successfully");
    }

    void itemInInv(){
        bta.setMsgText("can't add the item since the item name already exist");
    }


}
