package Trade.Adaptor.RequestTrade;

import Inventory.Adaptor.iItemController;
import Inventory.UseCase.Inventory;
import Inventory.Entity.Item;
import Trade.Adaptor.*;
import User.UseCase.UserManager;
import Trade.UseCase.TradeManager;
import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class SelectController implements iItemController {
    UUID currUser;
    UserManager um;
    Inventory iv;
    String currItem;
    BorderGUI bta;
    TradeManager tm;
    iSelectPresenter sp;
    List<String> wishList;
    JFrame fr;

    public SelectController(String currUser, BorderGUI bta, JFrame fr){
        this.um = new UserManager();
        this.iv = new Inventory();
        this.bta = bta;
        this.tm = new TradeManager();
        sp = new SelectPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }

    @Override
    public void updateCurr() {
        sp.notItemSelected();
    }

    public String printList(){
        String result = "";
        for (String it: um.getWishBorrow(currUser)){
            result = result + it + "\n";
        }
        return result;
    }

    boolean checkInput(String str){
        return um.getWishBorrow(currUser).contains(str);
    }



    public String getItemInfo(Item it){
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID()) ;

    }

    @Override
    public boolean isInList(String name) {
        return false;
    }

    void enterRTradeGUI(){
        if (currItem == null){
            sp.notItemSelected();
        }else{
            bta.getFrame().setVisible(false);
            BorderGUIBuilder builder = new RTradeGUIBuilder(currUser, currItem, bta.getFrame());
            BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
            engineer.constructGUI();
            GUIPlan tg = engineer.getGUI();
            tg.run();
            sp.closeFrame();
        }


    }

    List<String> getWishBorrow(){
        wishList = um.getWishBorrow(currUser);
        return wishList;
    }

    public void updateList(){
        sp.updateFrame(getWishBorrow());
    }

    @Override
    public void updateBut() {
        updateList();
        updateCurr();

    }

    @Override
    public void performActionOne() {
        enterRTradeGUI();
    }

    @Override
    public void performActionTwo() {

    }

    @Override
    public void performActionThree() {

    }


    public void submitBut(){
        String name = bta.getInput("input");
        sp.resetInputArea();
        if (!checkInput(name)){
            sp.wrongInput();
        }else{
            currItem = name;
            sp.selectItemInfo(getItemInfo(iv.getItem(name)));
            sp.updateSuccess();
        }
    }

    public void backBut(){
        fr.setVisible(true);
        sp.closeFrame();
    }
}
