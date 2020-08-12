package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUI;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

public class WishLendController implements iItemController {
    /**
     * the inventory of the system.
     */
    private Inventory iv;
    /**
     * the user that is using the system.
     */
    private UUID currUser;

    UserManager um;

    ApprovalManager iam;

    BorderGUI bta;

    String it;

    iItemPresenter ip;

    JFrame fr;

    /**
     * [constructor]
     * @param currUser current user
     */
    public WishLendController(String currUser, BorderGUI bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        iam = new ApprovalManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }




    public void deleteItemL(String it){
        if (iv.deleteItem(it)){
            ClientUser user = um.popUser(currUser);
            user.getWishLend().remove(it);
            um.addUser(user);
        }
    }

    private void setDescription(String des, String itemName){
        Item item = iv.popItem(itemName);
        item.setDescription(des);
        iv.addItem(item);
    }

    void delBut(){
        if (it == null){
            ip.noItemSelected();
        }else{
            deleteItemL(it);
            ip.noItemSelected();
            ip.delSuccess(it);
            ip.resetCurr();
            it = null;
            updateBut();
        }
    }

    public void updateCurr(){
        ip.resetCurr();
    }

    @Override
    public String printList() {
        String result = "";
        UserManager um = new UserManager();
        for (String it: um.getUser(currUser).getWishLend()){
            result = result + it + "\n";
        }
        if (result.equals("")){
            return "no item";
        }else{
            return result;
        }
    }

    public void updateList(){ ip.updateList(printList()); }

    void addBut(){
        String itemName = bta.getInput("name");
        String description = bta.getInput("des");
        if (itemName.equals("")) {
            ip.voidItem();
        }else if(iv.itemExist(itemName)){
            ip.nameUsed();
        }
        else {
            addToList(itemName, description);
            ip.resetCurr();
            ip.requestSuccess(itemName);
        }

    }

    public void addToList(String name, String des){
        iam.addApprovals(um.getUser(currUser),name,des);
    }

    public void updateBut(){
        updateList();
        updateCurr();
    }

    @Override
    public void performActionOne() {
        addBut();

    }

    @Override
    public void performActionTwo() {
        delBut();
    }

    @Override
    public void performActionThree() {
        editDes();
    }

    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishLend(currUser).contains(input)){
            ip.wrongInput();;
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
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

    @Override
    public boolean isInList(String name) {
        return false;
    }

    void editDes(){
        if (it == null){
            ip.noItemSelected();
        }else{
            String description = bta.getInput("des");
            setDescription(description, it);
            ip.editDesSuccess(it);
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }
}
