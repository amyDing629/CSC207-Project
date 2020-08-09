package Inventory;

import Trade.BorderGUIWithThreeTextArea;
import User.ClientUser;

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
        return "Item Info:\nitem name: " + iv.getName(item) + "\n" +
                "item description: " + iv.getDescription(item)
                + "\n" + "item owner: " + iv.getOwnerName(item) ;
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
