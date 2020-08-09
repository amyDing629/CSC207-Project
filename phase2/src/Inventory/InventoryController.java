package Inventory;
import Trade.BorderGUIBuilder;
import Trade.BorderGUIWithThreeTextArea;
import Trade.TradeGUIEngineer;
import Trade.TradeGUIPlan;
import User.Entity.ClientUser;
import User.Gateway.DataAccess;
import User.Gateway.UserDataAccess;
import User.UseCase.ItemApprovalManager;
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

    ItemApprovalManager iam;

    BorderGUIWithThreeTextArea bta;

    String it;

    InventoryPresenter ip;

    JFrame fr;

    /**
     * [constructor]
     * @param currUser current user
     */
    public InventoryController(UUID currUser, BorderGUIWithThreeTextArea bta, JFrame fr){
        this.currUser = currUser;
        iv = new Inventory();
        um = new UserManager();
        iam = new ItemApprovalManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
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

    void addToWishLend(Item it) throws FileNotFoundException {
        um.getWishLend(iv.getOwnerUUID(it.getName())).add(it.getName());
        iv.add(it);
        iam.removeItem(it.getName());
    }

    void removeItemFromIam(Item it){
        iam.removeItem(iv.getName(it));
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

    public Item getItemByRequestList(String itemName){
        for (ArrayList<String> strings: iam.getItemApproval()){
            if (strings.get(1).equals(itemName)){
                Item it = iv.createItem(strings.get(1), um.nameToUUID(strings.get(3)));
                it.setDescription(strings.get(2));
                return it;
            }
        }
        return null;
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
                result = new Item(strings.get(1), UUID.fromString(strings.get(3)));
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
        ArrayList<String> b= new ArrayList<>();
        b.add("1");
        b.add(name);
        b.add(des);
        b.add(um.getUsername(currUser));
        iam.getItemApproval().add(b);
    }

    Item createItem(String name){
        return iv.createItem(name, currUser);
    }

    public void setDescription(String des, String itemName){
        iv.setDescription(itemName, des);
    }



    void delButB() throws FileNotFoundException {
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


    void delButL() throws FileNotFoundException {
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
        BorderGUIBuilder builder = new WishBorrowAddBuilder(currUser, bta.getFrame());
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

    void submitButB(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishBorrow(currUser).contains(input)){
            ip.wrongInput();;
        }else{
            it = input;
            ip.updateCurr(getItemInfo());
        }
    }

    void submitButL(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishLend(currUser).contains(input)){
            ip.wrongInput();;
        }else{
            it = input;
            ip.updateCurr(getItemInfo());
        }
    }

    void submitButM(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        System.out.println("submitButM: " + iv.getAvailableList());
        if (!iv.getAvailableList().contains(input)){
            ip.wrongInput();;
        }else{
            it = input;
            ip.updateCurr(getItemInfo());
        }
    }

    void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    String getItemInfo() {
        Item item = iv.getItem(it);
        return "Item Info:\nitem name: " + iv.getName(item) + "\n" +
                "item description: " + iv.getDescription(item)
                + "\n" + "item owner: " + um.UUIDToName(item.getOwnerUUID());
    }

    void editDes(){
        if (it == null){
            ip.noItemSelected();
        }else{
            String description = bta.getInput("des");
            setDescription(description, it);
            ip.editDesSuccess(it);
            ip.updateCurr(getItemInfo());
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

    void agreeBut() throws FileNotFoundException {
        if (it == null){
            ip.noItemSelected();
        }else{
            addToWishLend(getItemByRequestList(it));
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
            removeItemFromIam(getItem(it));
            ip.resetCurr();
            it = null;
            ip.updateListM(printRequest());
        }
    }









}
