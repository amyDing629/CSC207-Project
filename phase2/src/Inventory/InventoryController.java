package Inventory;
import Trade.BorderGUIBuilder;
import Trade.BorderGUIWithThreeTextArea;
import Trade.TradeGUIEngineer;
import Trade.TradeGUIPlan;
import User.Entity.ClientUser;
import User.Entity.ItemApprovals;
import User.Gateway.DataAccess;
import User.Gateway.UserDataAccess;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private final UUID currUser;

    UserManager um;

    ApprovalManager iam;

    BorderGUIWithThreeTextArea bta;

    String it;

    InventoryPresenter ip;

    JFrame fr;

    /**
     * [constructor]
     * @param currUser current user
     */
    public InventoryController(String currUser, BorderGUIWithThreeTextArea bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        iam = new ApprovalManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }


    boolean isInInventory(String name){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * if the input item is the user's own item, return true. Else, return false.
     * @return whether the input item is the user's own item.
     */
    boolean isOwnItem(){
        return iv.getOwnerUUID(it).equals(currUser);
    }

    /**
     * move the selected item to user's wishBorrow list.
     */
    void moveToWishList(){
        um.getUser(currUser).addWishBorrow(it);
    }

    void addToWishLend(Item it) {
        if (!isInInventory(it.getName())){
            iv.add(it);
            ClientUser u = um.popUser(it.getOwnerUUID());
            u.getWishLend().add(it.getName());
            iam.removeItemApproval(it.getName());
            um.addUser(u);
        }else{
            ip.itemInInv();
        }

    }

    void removeItemFromIam(Item it){
        iam.removeItemApproval(iv.getName(it));
    }

    /**
     * @return whether the item is the currUser's wish list
     */
    boolean isInOwnWishList(){
        return um.getWishBorrow(currUser).contains(it);
    }

    /**
     * @param line item name
     * @return item
     */
    Item getItem(String line){
        return iv.getItem(line);
    }


    public String printRequest() {
        String result = iam.AllItemApprovals();
        if (result.equals("")) {
            return "no requested items";
        }
        return result;

    }

    public Item getItemByRequestList(String itemName){
        System.out.println(iam.getItemApprovals());
        ItemApprovals k=iam.getItemApproval(itemName);
        System.out.println(k);

        return iv.createItem(itemName,um.nameToUUID(k.getCurUserName()));
    }

    public boolean iamCheckInput(String name){
        return iam.hasItemApprovals(name);
    }


    public List<String> getWishLend(){
        return um.getWishLend(currUser);
    }

    public List<String> getWishBorrow(){
        return um.getWishBorrow(currUser);
    }

    public void deleteItemL(String it){
        if (iv.deleteItem(it)){
            ClientUser user = um.popUser(currUser);
            user.getWishLend().remove(it);
            um.addUser(user);
        }
    }

    public void deleteItemB(String it){
        ClientUser user = um.popUser(currUser);
        user.getWishBorrow().remove(it);
        um.addUser(user);
    }

    public void addItem(String name, String des){
        iam.addApprovals(um.getUser(currUser),name,des);
    }


    public void setDescription(String des, String itemName){
        iv.setDescription(itemName, des);
    }



    void delButB() {
        if (it == null){
            ip.noItemSelected();
        }else {
            deleteItemB(it);
            ip.noItemSelected();
            ip.delSuccess(it);
            ip.resetCurr();
            it = null;
            ip.updateListB(currUser);
        }
    }


    void delButL(){
        if (it == null){
            ip.noItemSelected();
        }else{
            deleteItemL(it);
            ip.noItemSelected();
            ip.delSuccess(it);
            ip.resetCurr();
            it = null;
            ip.updateListL(currUser);
        }
    }


    void updateCurr(){
        ip.resetCurr();
    }

    void updateLstB(){
        ip.updateListB(currUser);
    }

    void updateLstL(){
        ip.updateListL(currUser);
    }


    void addButB(){
        it = null;
        ip.updateListB(currUser);
        ip.closeFrame();
        BorderGUIBuilder builder = new WishBorrowAddBuilder(um.UUIDToName(currUser), bta.getFrame());
        TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
        engineer.constructGUI();
        TradeGUIPlan tg = engineer.getGUI();
        tg.run();
    }

    void addButL(){
        String itemName = bta.getInput("name");
        String description = bta.getInput("des");
        if (itemName.equals("")) {
            ip.voidItem();
        }else if(iv.itemExist(itemName)){
            ip.nameUsed();
        }
        else {
            addItem(itemName, description);
            ip.resetCurr();
            ip.requestSuccess(itemName);
        }

    }

    public void updateButB(){
        updateLstB();
        updateCurr();
    }

    public void updateButL(){
        updateLstL();
        updateCurr();
    }

    public void updateButR(){
    }

    public void updateListR(){
        ip.updateListM(printRequest());

    }

    void submitButB(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishBorrow(currUser).contains(input)){
            ip.wrongInput();;
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    void submitButR(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!iamCheckInput(input)){
            ip.wrongInput();
        }else{
            it = input;
            System.out.println(it);
            Item item = getItemByRequestList(input);
            System.out.println(item);
            ip.updateCurr(getItemInfo(item));
        }
    }

    void submitButL(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishLend(currUser).contains(input)){
            ip.wrongInput();;
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    void submitButM(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        System.out.println("submitButM: " + iv.getAvailableList());
        if (!iv.getAvailableList().contains(input)){
            ip.wrongInput();
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    String getItemInfo(Item it) {
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
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

    String printAvailable(){
        String result = "";
        for (String it: iv.getAvailableList()){
            result = result + it + "\n";
        }
        if (result.equals("")){
            return "no available item";
        }
        return result;

    }

    void updateListM(){
        ip.updateListM(printAvailable());
    }

    void addToWishBorrow(){
        if (isOwnItem()) {
            ip.addToWishBorrow(false);
        } else if (isInOwnWishList()) {
            ip.isInWishBorrow();
        } else {
            moveToWishList();
            ip.updateListM(printAvailable());
            ip.addToWishBorrow(true);
        }
    }

    void agreeBut(){
        if (it == null){
            ip.noItemSelected();
        }else{
            addToWishLend(getItemByRequestList(it));
            iam.removeItemApproval(it);
            ip.resetCurr();
            ip.addLSuccess();
            it = null;
            ip.updateListM(printRequest());
        }
    }

    void disagreeBut(){
        if (it == null) {
            ip.noItemSelected();
        }else{
            System.out.println(iam.getItemApprovals());
            iam.removeItemApproval(it);
            System.out.println(iam.getItemApprovals());
            ip.resetCurr();
            it = null;
            ip.updateListM(printRequest());
        }
    }









}
