package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUI;
import User.Entity.ClientUser;
import User.Entity.ItemApprovals;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;

public class AgreeReqController implements iItemController {
    /**
     * the inventory of the system.
     */
    private final Inventory iv;

    private final UserManager um;

    private final ApprovalManager iam;

    private final BorderGUI bta;

    private String it;

    private final iItemPresenter ip;

    private final JFrame fr;

    /**
     * [constructor]
     */
    public AgreeReqController (BorderGUI bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        iam = new ApprovalManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
    }

    public String printList() {
        String result = iam.AllItemApprovals();
        if (result.equals("")) {
            return "no requested items";
        }
        return result;

    }

    private Item getItemByRequestList(String itemName){
        ItemApprovals k=iam.getItemApproval(itemName);
        Item item = iv.createItem(itemName,um.nameToUUID(k.getCurUserName()));
        item.setDescription(k.getSecString());
        return item;
    }

    private boolean iamCheckInput(String name){
        return iam.hasItemApprovals(name);
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

    private void addToList(Item it) {
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
