package Trade;

import Inventory.Item;
import Trade.MeetingSystem.MeetingStatus;
import User.User;
import User.*;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * [controller]
 * the action of trade depend on the input of user
 */
public class TradeController {
    private final User currUser;
    private User tarUser;
    private final TradeManager tm = new TradeManager();
    private Trade currTrade;

    /**
     * [constructor]
     * @param currUser the user that is using the system
     */
    TradeController(User currUser) throws IOException {
        this.currUser = currUser;
    }

    TradeController(User currUser, Trade currTrade){
        this.currUser = currUser;
        this.currTrade = currTrade;
    }




    /**
     * get owner of the item
     * @param item current item selected by currUser
     * @return target user
     * @throws IOException can not find the target user
     */
    User getTarUser(Item item) throws IOException {
        UserManager um = new UserManager();
        tarUser = um.getUser(item.getOwnerName());
        return tarUser;
    }


    /**
     * check the frozen status of two users.
     * @throws IOException one of the users's account is frozen
     */
    void checkInput() throws IOException {
        if (currUser.getIsFrozen()) {
            throw new IOException("your account is frozen!");
        }
        if (tarUser == null){
            System.out.println("tarUser not found");
        };
        if (tarUser.getIsFrozen()) {
            throw new IOException("the account of the item owner is frozen!");
        }
        if (tarUser == currUser){
            throw new IOException("you cannot make trade with yourself");
        }
    }

    /**
     * create trade according to the user's input
     * @param line the user's input
     * @param item the item of the trade
     * @return whether or not the trade is a oneway trade
     * @throws IOException the trade is not created
     */
    boolean createTrade(String line, Item item) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        item.setIsInTrade(true);
        switch (line) {
            case "1":
                tm.createOnewayTrade(currUser.getId(), tarUser.getId(), item, 30, time);
                return true;
            case "2":
                tm.createOnewayTrade(currUser.getId(), tarUser.getId(), item, -1, time);
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
    void createTrade(String line, Item item1, Item item2) throws IOException {
        item1.setIsInTrade(true);
        item2.setIsInTrade(true);
        LocalDateTime time = LocalDateTime.now();
        if (line.equals("3")){
            tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item1, item2, 30, time);
        }else{
            tm.createTwowayTrade(currUser.getId(), tarUser.getId(), item1, item2, -1, time);
        }
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
     * @throws IOException if the item is not deleted from user's wishlist and inventory
     */
    void completeTrade() throws IOException {
        tm.completeTrade(currTrade);
    }

    /**
     * set the status of trade to cancelled
     */
    void cancelTrade(){
        tm.cancelTrade(currTrade);
    }

}

