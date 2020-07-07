package Trade;

import Inventory.Item;
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
    private final UserManager um = new UserManager();

    /**
     * [constructor]
     * @param currUser the user that is using the system
     */
    public TradeController(User currUser){
        this.currUser = currUser;

    }


    /**
     * get owner of the item
     * @param item current item selected by currUser
     * @return target user
     * @throws IOException can not find the target user
     */
    public User getTarUser(Item item) throws IOException {
        tarUser = um.getUser(item.getOwnerName());
        return tarUser;
    }


    /**
     * check the frozen status of two users.
     * @throws IOException one of the users's account is frozen
     */
    public void checkInput() throws IOException {
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
     * @throws IOException
     */
    public boolean createTrade(String line, Item item) throws IOException {
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
    public void createTrade(String line, Item item1, Item item2) throws IOException {
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
     * @param currTrade the current trade
     * @return the status
     */
    public String checkTradeMeeting(Trade currTrade) {
        if (currTrade.getStatus().equals("unconfirmed")) {
            return "confirm trade";
        }else if (currTrade.getStatus().equals("cancelled")) {
            return "cancelled";
        }else if (currTrade.getStatus().equals("complete")) {
            return "complete";
        }else if (currTrade.getMeeting().getStatus().equals("cancelled")){
            currTrade.setStatus("cancelled");
            return "cancelled";
        }else if (currTrade.getMeeting() == null || currTrade.getMeeting().getStatus().equals("incomplete")){
            return "first meeting";
        }else if (currTrade.getDuration()==Trade.temp){
            if (currTrade.getSecondMeeting().getStatus().equals("incomplete")){
                return "second meeting";
            }else{
                currTrade.setStatus("complete");
                return "complete";
            }
        }else{
            return "complete";
        }
    }

    /**
     * confirm trade(agree with the trade)
     * @param currTrade current trade
     */
    public void confirmTrade(Trade currTrade) {
        currTrade.setStatus("incomplete");
    }

    /**
     * set the status of trade to complete and make trade
     * @param currTrade current trade
     * @throws IOException if the item is not deleted from user's wishlist and inventory
     */
    public void completeTrade(Trade currTrade) throws IOException {
        currTrade.setStatus("complete");
        currTrade.makeTrade();
    }

    /**
     * set the status of trade to cancelled
     * @param currTrade current trade
     */
    public void cancelTrade(Trade currTrade){
        currTrade.setStatus("cancelled");
    }
}

