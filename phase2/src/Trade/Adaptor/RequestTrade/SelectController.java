package Trade.Adaptor.RequestTrade;

import Inventory.UseCase.Inventory;
import Inventory.Entity.Item;
import Trade.Adaptor.*;
import User.UseCase.UserManager;
import Trade.UseCase.TradeManager;
import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class SelectController {
    UUID currUser;
    UserManager um;
    Inventory iv;
    String currItem;
    BorderGUIWithThreeTextArea bta;
    TradeManager tm;
    SelectPresenter sp;
    List<String> wishList;
    JFrame fr;

    public SelectController(String currUser, BorderGUIWithThreeTextArea bta, JFrame fr){
        this.um = new UserManager();
        this.iv = new Inventory();
        this.bta = bta;
        this.tm = new TradeManager();
        sp = new SelectPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
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
        currItem = str;
        Item it = iv.getItem(str);
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID()) ;

    }

    void enterRTradeGUI(){
        if (currItem == null){
            sp.notItemSelected();
        }else{
            bta.getFrame().setVisible(false);
            BorderGUIBuilder builder = new RTradeGUIBuilder(currUser, currItem, bta.getFrame());
            TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
            engineer.constructGUI();
            TradeGUIPlan tg = engineer.getGUI();
            tg.run();
            sp.closeFrame();
        }


    }

    List<String> getWishBorrow(){
        wishList = um.getWishBorrow(currUser);
        return wishList;
    }

    void updateList(){
        sp.updateFrame(getWishBorrow());
    }


    void submitBut(){
        String name = bta.getInput("input");
        sp.resetInputArea();
        if (!checkInput(name)){
            sp.wrongInput();
        }else{
            currItem = name;
            sp.selectItemInfo(getItemInfo(name));
            sp.updateSuccess();
        }
    }

    void backBut(){
        fr.setVisible(true);
        sp.closeFrame();
    }
}
