package Inventory;
import Trade.BorderGUIBuilder;
import Trade.BorderGUIWithThreeTextArea;
import Trade.TradeGUIEngineer;
import Trade.TradeGUIPlan;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import javax.swing.*;
import java.io.IOException;
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

    BorderGUIWithThreeTextArea bta;

    Item it;

    InventoryPresenter ip;

    JFrame fr;

    /**
     * [constructor]
     * @param currUser current user
     */
    public InventoryController(ClientUser currUser, BorderGUIWithThreeTextArea bta, JFrame fr){
        this.currUser = currUser;
        this.iv = new Inventory();
        this.um = new UserManager();
        this.iam = new ItemApprovalManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
    }


    /**
     * if the input item is the user's own item, return true. Else, return false.
     * @return whether the input item is the user's own item.
     */
    boolean isOwnItem(){
        return iv.getOwnerName(it).equals(currUser.getUsername());
    }

    /**
     * move the selected item to user's wishBorrow list.
     */
    void moveToWishList(){
        currUser.addWishBorrow(iv.getName(it));
    }

    void addToWishLend(){
        um.getWishLend(um.getUser(iv.getOwnerName(it))).add(iv.getName(it));
        iv.add(it);
        iam.removeItem(iv.getName(it));
    }

    void removeItemFromIam(Item it){
        iam.removeItem(iv.getName(it));
    }

    /**
     * @return whether the item is the currUser's wish list
     */
    boolean isInOwnWishList(){
        return currUser.getWishBorrow().contains(iv.getName(it));
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
        if (iv.deleteItem(it)){
            um.getWishLend(currUser).remove(iv.getName(it));
        }else{
            System.out.println("cannot deleteItemL");
        }


    }

    public void deleteItemB(Item it){um.getWishBorrow(currUser).remove(iv.getName(it));}

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



    void delButB(){
        if (it == null){
            ip.noItemSelected();
        }else{
            deleteItemB(it);
            ip.noItemSelected();
            ip.delSuccess(it.getName());
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
            ip.delSuccess(it.getName());
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
        addItem(it.getName(), it.getDescription());
        ip.resetCurr();
        ip.requestSuccess(it.getName());
        it = null;
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
            it = iv.getItem(input);
            ip.updateCurr(getItemInfo());
        }
    }

    void submitButL(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishLend(currUser).contains(input)){
            ip.wrongInput();;
        }else{
            it = iv.getItem(input);
            ip.updateCurr(getItemInfo());
        }
    }

    void submitButM(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!iv.getAvailableList().contains(iv.getItem(input))){
            ip.wrongInput();;
        }else{
            it = iv.getItem(input);
            ip.updateCurr(getItemInfo());
        }
    }

    void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    String getItemInfo() {
        /*
        System.out.println("item name: " + item.getName());
        System.out.println("item description: " + item.getDescription());
        System.out.println("item owner: " + item.getOwnerName());

         */
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + iv.getOwnerName(it) ;
    }


    void createBut(){
        String itemName = bta.getInput("name");
        String description = bta.getInput("des");
        if (itemName.equals("")) {
            ip.voidItem();
        }else if(iv.itemExist(itemName)){
            ip.nameUsed();
        }
        else {
            it = createItem(itemName);
            setDescription(description, it);
            ip.updateCurr(getItemInfo());
            ip.createSuccess(it.getName());
        }
    }

    void editDes(){
        if (it == null){
            ip.noItemSelected();
        }else{
            String description = bta.getInput("des");
            setDescription(description, it);
            ip.editDesSuccess(it.getName());
            ip.updateCurr(getItemInfo());
        }
    }

    String printAvailable(){
        String result = "";
        for (Item it: iv.getAvailableList()){
            result = result + iv.getName(it) + "\n";
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









}
