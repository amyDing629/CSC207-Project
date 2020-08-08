package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.ClientUser;
import User.*;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * [controller]
 * the action of trade depend on the input of user
 */
public class TradeController {
    private final ClientUser currUser;
    private ClientUser tarUser;
    private final TradeManager tm;
    private Trade currTrade;
    private final UserManager um;
    private Inventory iv;
    TradePresenter tp;
    BorderGUIWithThreeTextArea bta;
    Item it;

    /**
     * [constructor]
     * @param currUser the user that is using the system
     * @param tm the object that edits the trade list of input gateway
     * @param um the object that edits the user list of input gateway
     */
    TradeController(ClientUser currUser, TradeManager tm, UserManager um, Inventory iv,
                    BorderGUIWithThreeTextArea bta, Item item){
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
        this.iv = iv;
        tp = new TradePresenter(bta);
        this.bta = bta;
        it = item;

    }

    TradeController(ClientUser currUser, Trade currTrade, TradeManager tm, UserManager um){
        this.currUser = currUser;
        this.currTrade = currTrade;
        this.tm = tm;
        this.um = um;
    }




    /**
     * get owner of the item
     * @param item current item selected by currUser
     */
    void getTarUser(Item item){
        tarUser = um.getUser(item.getOwnerName());
    }


    /**
     * check the frozen status of two users.
     * @throws IOException one of the users's account is frozen
     */
    boolean checkInput(Item item){
        if (getIsInTrade(item)){
            tp.inTradeError();
            return false;
        }
        if (um.getIsFrozen(currUser)) {
            tp.currAccountFrozen();
            return false;
        }
        if (tarUser == null){
            tp.tarUserNotFound();;
            return false;
        }
        if (um.getIsFrozen(tarUser)) {
            tp.tarAccountFrozen();
            return false;
        }
        if (tarUser == currUser){
            tp.selfItem();
            return false;
        }
        return true;
    }

    /**
     * create trade according to the user's input
     * @param line the user's input
     * @param item the item of the trade
     * @return whether or not the trade is a oneway trade
     * @throws IOException the trade is not created
     */
    boolean createTrade(String line, Item item){
        TradeEditor te = new TradeEditor();
        LocalDateTime time = LocalDateTime.now();
        iv.setIsInTrade(item,true);
        switch (line) {
            case "1":
                currTrade = tm.createOnewayTrade(currUser.getId(), tarUser.getId(), item, 30, time);
                te.setCreator(currTrade, currUser.getId());
                updateTradeHistory();
                return true;
            case "2":
                currTrade = tm.createOnewayTrade(currUser.getId(), tarUser.getId(), item, -1, time);
                te.setCreator(currTrade, currUser.getId());
                updateTradeHistory();
                return true;
            default: {
                return false;
            }
        }
    }

    /**
     * create trade if the trade is a two way trade
     * @param line the input of users
     * @param item1 the first item
     * @param item2 the second item
     * @throws IOException if the trade is not created
     */
    void createTrade(String line, Item item1, Item item2){
        TradeEditor te = new TradeEditor();
        iv.setIsInTrade(item1,true);
        iv.setIsInTrade(item2,true);
        LocalDateTime time = LocalDateTime.now();
        if (line.equals("3")){
            currTrade = tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item1, item2, 30, time);
        }else{
            currTrade = tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item1, item2, -1, time);

        }
        te.setCreator(currTrade, currUser.getId());
        updateTradeHistory();
    }

    void updateTradeHistory() {
        TradeEditor te = new TradeEditor();
        // System.out.println("userList:"+userManager.getUser UserManager userManager = new UserManager(gw);
        currUser.getTradeHistory().add(te.getId(currTrade));
        tarUser.getTradeHistory().add(te.getId(currTrade));
    }
    /**
     * check the status of the current trade
     * @return the status
     */
//    String checkTradeMeeting() {
//        return tm.checkTradeMeeting(currTrade);
//    }
    /**
     * confirm trade(agree with the trade)
     */
    void confirmTrade() {
        tm.confirmTrade(currTrade);
    }

    /**
     * set the status of trade to complete and make trade
     */
    void completeTrade(){
        tm.completeTrade(currTrade);
    }

    /**
     * set the status of trade to cancelled
     */
    void cancelTrade(){
        tm.cancelTrade(currTrade);
    }

    Item getItem(String line){
        return iv.getItem(line);

    }

    ArrayList<String> getSuggestedItemName(){
        ArrayList<String> result = new ArrayList<>();
        for (String i: currUser.getWishLend()){
            if (tarUser.getWishBorrow().contains(i)){
                result.add(i);
            }
        }
        return result;
    }

    String printUnConfirmed(){
        String result = "";
        List<Trade> iU = tm.getUnconfirmed(currUser);
        for (int i = 0; i < iU.size(); i++) {
            result = result + i + ". " + iU.get(i).toString() + "\n";
        }
        return result;
    }

    boolean getIsInTrade(Item it){
        return iv.getIsInTrade(it);

    }

    void presentTradeInfo(){
        tp.presentTradeInfo(currUser, it, currUser.getWishLend(), getSuggestedItemName() );
    }

    void onewayBut(String duration){
        if (it == null){
            tp.wrongInput();
        }else if (checkInput(it)){
            if (duration.equals("temp")){
                createTrade("1", it);
                tp.createSuccess("(one way temporary");
            }else{
                createTrade("2", it);
                tp.createSuccess("(one way permanent");
            }

        }
    }

    void twowayBut(String duration){

        Item item = iv.getItem(bta.getInput());
        tp.updateInputArea();
        if (!currUser.getWishLend().contains(item.getName())){
            tp.wrongInput();
        }else if (checkInput(item)){
            if (duration.equals("temp")){
                createTrade("4", it, item);
                tp.createSuccess("(two way-permanent)");
            }else{
                createTrade("3", it, item);
                tp.createSuccess("two way-temporary");
            }
        }
    }

    void backBut(JFrame frame){
        frame.setVisible(true);
        tp.closeFrame();
    }












}

