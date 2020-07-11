package Trade;

import Inventory.Item;
import Main.GateWay;
import Trade.MeetingSystem.MeetingStatus;
import User.User;
import User.UserManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * This is a use case class trade.TradeManager
 * store Trades in system
 * Allow users to set up trade, cancel trade
 * Automatically update the trade history for both users in the trade.
 */
public class TradeManager {
    //private ArrayList<Trade> tradeList;


    /**
     * Allow the currentUser to create a one-way trade with input otherUserId, item, and trade duration.
     * Update the trade history for both users
     * @param otherUserId the userId of another user.User in the particular trade.Trade.
     * @param item the only one Inventory.Item to be trade in this created trade.Trade.
     * @param duration the duration of this trade.Trade, unit (days). -1 means the trade.Trade is permanent.
     */
    Trade createOnewayTrade(UUID currUserId, UUID otherUserId, Item item, int duration, LocalDateTime time)
            throws IOException {
        OnewayTrade newTrade = new OnewayTrade(currUserId, otherUserId, item, duration, time);
        GateWay.trades.add(newTrade);
        // Record this new trade.Trade in system

        //Update trade history for both users
        updateTradeHistory(currUserId, otherUserId, newTrade);
        return newTrade;

    }

    /**
     * if the user.User wants to make a two way trade, tradeManager will create a two way trade.
     * @param otherUserId the userId of another user.User in the particular trade.Trade
     * @param item1to2 the Inventory.Item to be trade in this created trade.Trade.
     * @param item2to1 the other Inventory.Item to be trade in this created trade.Trade.
     * @param duration the duration of this trade.Trade, unit (days). -1 means the trade.Trade is permanent.
     */
    void createTwowayTrade(UUID currUserId, UUID otherUserId, Item item1to2, Item item2to1, int duration,
                                  LocalDateTime time) throws IOException {
        TwowayTrade newTrade = new TwowayTrade(currUserId, otherUserId, item1to2, item2to1, duration, time);
        GateWay.trades.add(newTrade);
        // Update trade history for both users
        updateTradeHistory(currUserId, otherUserId, newTrade);
    }



    void updateTradeHistory(UUID currUserId, UUID tarUserId, Trade newTrade) throws IOException {
        // System.out.println("userList:"+userManager.getUserList());
        UserManager userManager = new UserManager();
        User currentUser = userManager.getUser(currUserId);
        User tarUser = userManager.getUser(tarUserId);
        System.out.println(currentUser);
        System.out.println(GateWay.trades);
        currentUser.getTradeHistory().add(newTrade.getId());
        tarUser.getTradeHistory().add(newTrade.getId());
    }


    public Trade getTrade(UUID id) {
        for (Trade trade : GateWay.trades) {
            if (trade.getId().equals(id)) {
                return trade;
            }
        }
        return null;
    }

    /**
     * check the status of the current trade
     * @param currTrade the current trade
     * @return the status
     */
    String checkTradeMeeting(Trade currTrade) {
        if (currTrade.getStatus().equals("unconfirmed")) {
            return "confirm trade";
        }else if (currTrade.getStatus().equals("cancelled")) {
            return "cancelled";
        }else if (currTrade.getStatus().equals("complete")) {
            return "complete";

        }else if (currTrade.getMeeting() == null ||
                currTrade.getMeeting().getStatus() == MeetingStatus.incomplete ||
                currTrade.getMeeting().getStatus() == MeetingStatus.agreed){
            return "first meeting";
        }else if (currTrade.getMeeting().getStatus() == MeetingStatus.cancelled){
            currTrade.setStatus("cancelled");
            return "cancelled";
        }else if (currTrade.getDuration()==Trade.temp){
            if (currTrade.getSecondMeeting().getStatus() == MeetingStatus.incomplete){
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
    void confirmTrade(Trade currTrade) {
        currTrade.setStatus("incomplete");
    }

    /**
     * set the status of trade to complete and make trade
     * @param currTrade current trade
     * @throws IOException if the item is not deleted from user's wishlist and inventory
     */
    void completeTrade(Trade currTrade) throws IOException {
        currTrade.setStatus("complete");
        currTrade.makeTrade();
    }

    /**
     * set the status of trade to cancelled
     * @param currTrade current trade
     */
    void cancelTrade(Trade currTrade){
        currTrade.setStatus("cancelled");
    }








}

