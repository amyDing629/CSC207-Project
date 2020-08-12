package Inventory.Adaptor;

import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUI;
import User.UseCase.UserManager;
import Inventory.Entity.Item;
import javax.swing.*;

public class MarketController implements iItemController {
    Inventory iv;
    BorderGUI bta;
    JFrame fr;
    String it;
    iMarketPresenter ip;
    public MarketController(BorderGUI bta, JFrame fr){
        this.iv = new Inventory();
        this.bta = bta;
        ip = new MarketPresenter(bta);
        this.fr = fr;
    }


    public String printList(){
        StringBuilder result = new StringBuilder();
        for (String it: iv.getAvailableList()){
            result.append(it).append("\n");
        }
        if (result.toString().equals("")){
            return "no available item";
        }
        return result.toString();

    }


    public String getItemInfo(Item it) {
        UserManager um = new UserManager();
        if (iv.getName(it) == null) {
            return "No Item Selected";
        } else {
            return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                    "item description: " + iv.getDescription(it)
                    + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
        }
    }

    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        System.out.println("market controller" + iv.getAvailableList() + iv.getItem(input));
        System.out.println(iv.getAvailableList().contains(input));
        if (!iv.getAvailableList().contains(input)){
            ip.wrongInput();
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    public void updateList(){
        ip.updateListM(printList());
    }

    @Override
    public void updateBut() {
        updateList();
        updateCurr();
    }


    public void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }


    @Override
    public boolean isInList(String name){
        return false;
    }

    public void updateCurr(){
        ip.resetCurr();
    }

    @Override
    public void performActionOne() { }

    @Override
    public void performActionTwo() { }

    @Override
    public void performActionThree() {
    }



}
