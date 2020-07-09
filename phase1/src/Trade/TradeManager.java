package Trade;

import Inventory.Inventory;
import Inventory.Item;
import Main.GateWay;
import Trade.MeetingSystem.MeetingEditor;
import Trade.MeetingSystem.Meeting;
import Trade.MeetingSystem.MeetingStatus;
import User.User;
import User.UserManager;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * This is a use case class trade.TradeManager
 * store Trades in system
 * Allow users to set up trade, cancel trade
 * Automatically update the trade history for both users in the trade.
 */
public class TradeManager {
    //private ArrayList<Trade> tradeList;
    private UserManager userManager;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Constructor
     * tradeList stores all the trade.Trade in the system.
     * currentUser is the user.User who is currently interacting with this trade.TradeManager.
     */
    public TradeManager() throws IOException {
        userManager = new UserManager();
    }

    public ArrayList<Trade> getTradeList(){
        return GateWay.trades;
    }


    /**
     * Allow the currentUser to create a one-way trade with input otherUserId, item, and trade duration.
     * Update the trade history for both users
     * @param otherUserId the userId of another user.User in the particular trade.Trade.
     * @param item the only one Inventory.Item to be trade in this created trade.Trade.
     * @param duration the duration of this trade.Trade, unit (days). -1 means the trade.Trade is permanent.
     */
    public Trade createOnewayTrade(UUID currUserId, UUID otherUserId, Item item, int duration, LocalDateTime time)
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
    public void createTwowayTrade(UUID currUserId, UUID otherUserId, Item item1to2, Item item2to1, int duration,
                                  LocalDateTime time) throws IOException {
        TwowayTrade newTrade = new TwowayTrade(currUserId, otherUserId, item1to2, item2to1, duration, time);
        GateWay.trades.add(newTrade);
        // Update trade history for both users
        updateTradeHistory(currUserId, otherUserId, newTrade);
    }



    public void updateTradeHistory(UUID currUserId, UUID tarUserId, Trade newTrade) throws IOException {
        // System.out.println("userList:"+userManager.getUserList());
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







}

