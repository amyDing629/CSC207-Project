package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SelectController {
    ClientUser currUser;
    UserManager um;
    Inventory iv;
    Item currItem;
    BorderGUIWithThreeTextArea bta;
    TradeManager tm;
    SelectPresenter sp;
    List<String> wishList;
    JFrame fr;

    public SelectController(ClientUser currUser, TradeManager tm, UserManager um, Inventory iv,
                            BorderGUIWithThreeTextArea bta, JFrame fr){
        this.currUser = currUser;
        this.um = um;
        this.iv = iv;
        this.bta = bta;
        this.tm = tm;
        sp = new SelectPresenter(bta);
        this.fr = fr;
    }

    public String getWishList(){
        String result = "";
        for (String it: um.getWishBorrow(currUser)){
            result = result + it + "\n";
        }
        return result;
    }

    boolean checkInput(String str){
        return um.getWishBorrow(currUser).contains(str);
    }

    Item getItem(String str){
        return iv.getItem(str);
    }


    String getItemInfo(String str){
        Item item = iv.getItem(str);
        currItem = item;
        return "Item Info:\nitem name: " + iv.getName(item) + "\n" +
                "item description: " + iv.getDescription(item)
                + "\n" + "item owner: " + item.getOwnerName() ;

    }

    void enterRTradeGUI(){
        bta.getFrame().setVisible(false);
        BorderGUIBuilder builder = new RTradeGUIBuilder(currUser, currItem, tm, um, iv, bta.getFrame());
        TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
        engineer.constructGUI();
        TradeGUIPlan tg = engineer.getGUI();
        tg.run();
        sp.closeFrame();

    }

    List<String> getWishBorrow(){
        wishList = um.getWishBorrow(currUser);
        return wishList;
    }

    void updateList(){
        sp.updateFrame(getWishBorrow());
    }


    void submitBut(){
        String name = bta.getInput();
        sp.resetInputArea();
        if (!checkInput(name)){
            sp.wrongInput();
        }else{
            currItem = getItem(name);
            sp.selectItemInfo(getItemInfo(name));
            sp.updateSuccess();
        }
    }

    void backBut(){
        fr.setVisible(true);
        sp.closeFrame();
    }
}
