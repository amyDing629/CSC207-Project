package Inventory.Adaptor;

import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUIWithThreeTextArea;
import User.UseCase.UserManager;
import Inventory.Entity.Item;
import javax.swing.*;

public class MarketController {
    Inventory iv;
    BorderGUIWithThreeTextArea bta;
    JFrame fr;
    Item it;
    MarketPresenter ip;
    public MarketController(BorderGUIWithThreeTextArea bta, JFrame fr){
        this.iv = new Inventory();
        this.bta = bta;
        ip = new MarketPresenter(bta);
        this.fr = fr;
    }


    Item getItem(String line){
        return iv.getItem(line);
    }

    String wrongInput(){
        return "wrong input, please type again";
    }

    String printItemInfo(Item item) {
        UserManager um = new UserManager();
        return "Item Info:\nitem name: " + iv.getName(item) + "\n" +
                "item description: " + iv.getDescription(item)
                + "\n" + "item owner: " + um.UUIDToName(item.getOwnerUUID()) ;
    }

    String printAvailable(){
        StringBuilder result = new StringBuilder();
        for (String it: iv.getAvailableList()){
            result.append(it).append("\n");
        }
        if (result.toString().equals("")){
            return "no available item";
        }
        return result.toString();

    }

    String getItemInfo() {
        UserManager um = new UserManager();
        if (iv.getName(it) == null) {
            return "No Item Selected";
        } else {
            System.out.println(iv.getName(it));
            System.out.println(iv.getDescription(it));
            System.out.println(it.getOwnerUUID());
            return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                    "item description: " + iv.getDescription(it)
                    + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
        }
    }

    void submitButM(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        System.out.println("market controller" + iv.getAvailableList() + iv.getItem(input));
        System.out.println(iv.getAvailableList().contains(input));
        if (!iv.getAvailableList().contains(input)){
            ip.wrongInput();
        }else{
            it = iv.getItem(input);
            ip.updateCurr(getItemInfo());
        }
    }

    void updateListM(){
        ip.updateListM(printAvailable());
    }

    void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    void updateCurr(){
        ip.resetCurr();
    }


}
