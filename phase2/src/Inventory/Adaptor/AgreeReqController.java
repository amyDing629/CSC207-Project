package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUIWithThreeTextArea;
import User.Entity.ClientUser;
import User.Entity.ItemApprovals;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

public class AgreeReqController implements iItemController {
    /**
     * the inventory of the system.
     */
    private final Inventory iv;
    /**
     * the user that is using the system.
     */
    private final UUID currUser;

    UserManager um;

    ApprovalManager iam;

    BorderGUIWithThreeTextArea bta;

    String it;

    iItemPresenter ip;

    JFrame fr;

    /**
     * [constructor]
     * @param currUser current user
     */
    public AgreeReqController (String currUser, BorderGUIWithThreeTextArea bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        iam = new ApprovalManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }

    void removeItemFromIam(Item it){
        iam.removeItemApproval(iv.getName(it));
    }

    public String printList() {
        String result = iam.AllItemApprovals();
        if (result.equals("")) {
            return "no requested items";
        }
        return result;

    }

    public Item getItemByRequestList(String itemName){
        ItemApprovals k=iam.getItemApproval(itemName);
        Item item = iv.createItem(itemName,um.nameToUUID(k.getCurUserName()));
        item.setDescription(k.getSecString());
        return item;
    }

    public boolean iamCheckInput(String name){
        return iam.hasItemApprovals(name);
    }

    public void addItem(String name, String des){
        iam.addApprovals(um.getUser(currUser),name,des);
    }

    public void updateCurr(){
        ip.resetCurr();
    }

    public void updateBut(){
        updateList();
    }

    @Override
    public void performActionOne() {
        agreeBut();
    }

    @Override
    public void performActionTwo() {
        disagreeBut();
    }

    @Override
    public void performActionThree() {

    }

    public void updateList(){
        ip.updateList(printList());

    }

    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!iamCheckInput(input)){
            ip.wrongInput();
        }else{
            it = input;
            Item item = getItemByRequestList(input);
            ip.updateCurr(getItemInfo(item));
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

    public void addToList(Item it) {
        if (!isInList(it.getName())){
            iv.add(it);
            ClientUser user = um.popUser(it.getOwnerUUID());
            user.getWishLend().add(it.getName());
            iam.removeItemApproval(it.getName());
            um.addUser(user);

        }else{
            ip.itemInInv();
        }
    }

    public boolean isInList(String name){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    void agreeBut(){
        if (it == null){
            ip.noItemSelected();
        }else{
            addToList(getItemByRequestList(it));
            iam.removeItemApproval(it);
            ip.resetCurr();
            ip.addLSuccess();
            it = null;
            updateList();
        }
    }

    void disagreeBut(){
        if (it == null) {
            ip.noItemSelected();
        }else{
            iam.removeItemApproval(it);
            ip.resetCurr();
            ip.delSuccess(it);
            it = null;
            updateList();
        }
    }
}
