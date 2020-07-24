package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.ClientUser;
import User.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    /**
     * [constructor]
     * @param currUser the user that is using the system
     * @param tm the object that edits the trade list of input gateway
     * @param um the object that edits the user list of input gateway
     */
    TradeController(ClientUser currUser, TradeManager tm, UserManager um, Inventory iv){
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
        this.iv = iv;
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
    String checkInput(Item item){
        if (item.getIsInTrade()){
            return "the item is already in the trade";
        }
        if (currUser.getIsFrozen()) {
            return "your account is frozen!";
        }
        if (tarUser == null){
            return "tarUser not found";
        }
        if (tarUser.getIsFrozen()) {
            return "the account of the item owner is frozen!";
        }
        if (tarUser == currUser){
            return "you cannot make trade with yourself";
        }
        return "true";
    }

    /**
     * create trade according to the user's input
     * @param line the user's input
     * @param item the item of the trade
     * @return whether or not the trade is a oneway trade
     * @throws IOException the trade is not created
     */
    boolean createTrade(String line, Item item){
        LocalDateTime time = LocalDateTime.now();
        item.setIsInTrade(true);
        switch (line) {
            case "1":
                currTrade = tm.createOnewayTrade(currUser.getId(), tarUser.getId(), item, 30, time);
                currTrade.setCreator(currUser.getId());
                updateTradeHistory();
                return true;
            case "2":
                currTrade = tm.createOnewayTrade(currUser.getId(), tarUser.getId(), item, -1, time);
                currTrade.setCreator(currUser.getId());
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
        item1.setIsInTrade(true);
        item2.setIsInTrade(true);
        LocalDateTime time = LocalDateTime.now();
        if (line.equals("3")){
            currTrade = tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item1, item2, 30, time);
            currTrade.setCreator(currUser.getId());
            updateTradeHistory();
        }else{
            currTrade = tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item1, item2, -1, time);
            currTrade.setCreator(currUser.getId());
            updateTradeHistory();

        }
    }

    void updateTradeHistory() {
        // System.out.println("userList:"+userManager.getUser UserManager userManager = new UserManager(gw);
        currUser.getTradeHistory().add(currTrade.getId());
        tarUser.getTradeHistory().add(currTrade.getId());
    }
    /**
     * check the status of the current trade
     * @return the status
     */
    String checkTradeMeeting() {
        return tm.checkTradeMeeting(currTrade);
    }
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



}

