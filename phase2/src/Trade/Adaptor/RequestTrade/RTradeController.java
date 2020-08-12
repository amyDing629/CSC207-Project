package Trade.Adaptor.RequestTrade;

import Inventory.UseCase.Inventory;
import Inventory.Entity.Item;
import Trade.Adaptor.BorderGUI;
import Trade.Entity.Trade;
import User.Entity.ClientUser;
import User.UseCase.UserManager;
import Trade.UseCase.TradeManager;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * [controller]
 * the action of trade depend on the input of user
 */
public class RTradeController {
    private final UUID currUser;
    private UUID tarUser;
    private TradeManager tm;
    private UUID currTrade;
    private final UserManager um;
    private final Inventory iv;
    RTradePresenter tp;
    BorderGUI bta;
    String it;

    /**
     * [constructor]
     * @param currUser the user that is using the system
     */
    RTradeController(UUID currUser,
                     BorderGUI bta, String item){
        this.currUser = currUser;
        this.tm = new TradeManager();
        this.um = new UserManager();
        this.iv = new Inventory();
        tp = new RTradePresenter(bta);
        this.bta = bta;
        it = item;

    }




    /**
     * get owner of the item
     * @param item current item selected by currUser
     */
    void getTarUser(String item){
        tarUser = iv.getItem(item).getOwnerUUID();
    }


    /**
     * check the frozen status of two users.
     * @throws IOException one of the users's account is frozen
     */
    boolean checkInput(String item){
        if (getIsInTrade(item)){
            tp.inTradeError();
            return false;
        }
        if (um.getIsFrozen(currUser)) {
            tp.currAccountFrozen();
            return false;
        }
        if (tarUser == null){
            tp.tarUserNotFound();
            return false;
        }
        if (um.getIsFrozen(tarUser)) {
            tp.tarAccountFrozen();
            return false;
        }
        if (tarUser.equals(currUser)){
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
    boolean createTrade(String line, String item){
        ClientUser user = um.getUser(currUser);
        LocalDateTime time = LocalDateTime.now();
        iv.setIsInTrade(item,true);
        switch (line) {
            case "1":
                currTrade = tm.createOnewayTrade(currUser, tarUser, it, 30, time);
                Trade trade = tm.popTrade(currTrade);
                trade.setCreator(currUser);
                tm.addTrade(trade);
                updateTradeHistory();
                return true;
            case "2":
                currTrade = tm.createOnewayTrade(currUser, tarUser, it, -1, time);
                Trade trade1 = tm.popTrade(currTrade);
                trade1.setCreator(currUser);
                tm.addTrade(trade1);
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
    void createTrade(String line, String item1, String item2){
        iv.setIsInTrade(item1,true);
        iv.setIsInTrade(item2,true);
        LocalDateTime time = LocalDateTime.now();
        if (line.equals("3")){
            currTrade = tm.createTwowayTrade(currUser, tarUser, item1, item2, 30, time);
            Trade trade = tm.popTrade(currTrade);
            tm.setCreator(trade.getId(), currUser);
            tm.addTrade(trade);
            updateTradeHistory();

        }else{
            currTrade = tm.createTwowayTrade(currUser, tarUser, item1, item2, -1, time);
            Trade trade = tm.popTrade(currTrade);
            tm.setCreator(trade.getId(), currUser);
            tm.addTrade(trade);
            updateTradeHistory();

        }
    }

    void updateTradeHistory() {
        // System.out.println("userList:"+userManager.getUser UserManager userManager = new UserManager(gw);
        ClientUser curr = um.popUser(currUser);
        ClientUser tar = um.popUser(tarUser);
        curr.getTradeHistory().add(currTrade);
        tar.getTradeHistory().add(currTrade);
        um.addUser(curr);
        um.addUser(tar);
    }
//    /**
//     * check the status of the current trade
//     * @return the status
//     */
//    String checkTradeMeeting() {
//        return tm.checkTradeMeeting(currTrade);
//    }
    /**
     * confirm trade(agree with the trade)
     */
    void confirmTrade() throws FileNotFoundException {
        tm.confirmTrade(currTrade);
    }

    /**
     * set the status of trade to complete and make trade
     */
    void completeTrade() throws FileNotFoundException {
        tm.completeTrade(currTrade);
    }

    /**
     * set the status of trade to cancelled
     */
    void cancelTrade() throws FileNotFoundException {
        tm.cancelTrade(currTrade);
    }

    Item getItem(String line){
        return iv.getItem(line);

    }

    ArrayList<String> getSuggestedItemName(){
        ArrayList<String> result = new ArrayList<>();
        for (String i: um.getWishLend(currUser)){
            if (um.getWishBorrow(tarUser).contains(i)){
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

    boolean getIsInTrade(String it){
        return iv.getIsInTrade(it);

    }

    void presentTradeInfo(){
        ClientUser user = um.getUser(currUser);
        Item item = iv.getItem(it);
        tp.presentTradeInfo(user, item, um.getWishLend(currUser), getSuggestedItemName() );
    }

    void onewayBut(String duration) throws FileNotFoundException {
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

    void twowayBut(String duration) throws FileNotFoundException {

        String item = bta.getInput("input");
        tp.updateInputArea();
        if (!um.getWishLend(currUser).contains(item)){
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

