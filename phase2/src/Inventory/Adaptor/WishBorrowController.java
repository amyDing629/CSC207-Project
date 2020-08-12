package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.TradeGUIEngineer;
import Trade.Adaptor.TradeGUIPlan;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

public class WishBorrowController implements iItemController {
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
    public WishBorrowController(String currUser, BorderGUI bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }


    public void deleteItem(String it){
        ClientUser user = um.popUser(currUser);
        user.getWishBorrow().remove(it);
        um.addUser(user);
    }

    public void delBut() {
        if (it == null){
            ip.noItemSelected();
        }else {
            deleteItem(it);
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

    public void updateList(){
        ip.updateList(printList());
    }

    public String printList(){
        System.out.println("wish borrow: " + um.getUser(currUser).getWishBorrow());
        StringBuilder result = new StringBuilder();
        for (String it: um.getUser(currUser).getWishBorrow()){
            result.append(it).append("\n");
        }
        if (result.toString().equals("")){
            return "no available item";
        }
        return result.toString();
    }


    void addBut(){
        it = null;
        updateBut();
        ip.closeFrame();
        BorderGUIBuilder builder = new WishBorrowAddBuilder(um.UUIDToName(currUser), bta.getFrame());
        TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
        engineer.constructGUI();
        TradeGUIPlan tg = engineer.getGUI();
        tg.run();
    }

    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishBorrow(currUser).contains(input)){
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

    }


}
