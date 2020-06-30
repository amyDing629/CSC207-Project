import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a use case class TradeManager
 * store Trades in system
 * Allow users to set up trade, cancel trade
 * Automatically update the trade history for both users in the trade.
 */
public class TradeManager {
    private ArrayList<Trade> tradeList;
    private ClientUser currentUser;
    private Integer currentUserId;
    private UserManager userManager;


    /**
     * Constructor
     * tradeList stores all the Trade in the system.
     * currentUser is the User who is currently interacting with this TradeManager.
     */
    public TradeManager(ClientUser currentUser){
        tradeList = new ArrayList<Trade>();
        this.currentUser = currentUser;
        currentUserId = currentUser.getId();
        userManager = new UserManager();
    }

    public ClientUser getCurrentUser() {
        return currentUser;
    }

    /**
     * Allow the currentUser to create a one-way trade with input otherUserId, item, and trade duration.
     * Update the trade history for both users
     * @param otherUserId the userId of another User in the particular Trade.
     * @param item the only one Item to be trade in this created Trade.
     * @param duration the duration of this Trade, unit (days). -1 means the Trade is permanent.
     * @return the created newTrade
     */
    public Trade createOnewayTrade(Integer otherUserId, Item item, int duration, LocalDateTime time){
        OnewayTrade newTrade = new OnewayTrade(currentUserId, otherUserId, item, duration, time);
        tradeList.add(newTrade); // Record this new Trade in system

        // Update trade history for both users
        this.updateTradeHistory(otherUserId, newTrade);
        return newTrade;
    }

    /**
     * if the User wants to make a two way trade, tradeManager will create a two way trade.
     * @param otherUserId the userId of another User in the particular Trade
     * @param item1to2 the Item to be trade in this created Trade.
     * @param item2to1 the other Item to be trade in this created Trade.
     * @param duration the duration of this Trade, unit (days). -1 means the Trade is permanent.
     * @return the created newTrade
     */
    public Trade createTwowayTrade(Integer otherUserId, Item item1to2, Item item2to1, int duration, LocalDateTime time){
        TwowayTrade newTrade = new TwowayTrade(currentUserId, otherUserId, item1to2, item2to1, duration, time);
        tradeList.add(newTrade);
        // Update trade history for both users
        this.updateTradeHistory(otherUserId, newTrade);
        return newTrade;
    }


    private void updateTradeHistory(Integer otherUserId, Trade newTrade){
        List<Trade> currentTradeHistory = userManager.findTrade(currentUserId);
        currentTradeHistory.add(newTrade);
        List<Trade> otherTradeHistory = userManager.findTrade(otherUserId);
        otherTradeHistory.add(newTrade);
    }


    public Trade getTrade(int id) {
        for (Trade trade : tradeList) {
            if (trade.getId() == id) {
                return trade;
            }
        }
        return null;
    }

    /**
     * abstract class
     * make trade happen, move items in User's list
     */
   // public void makeTrade(){

    //};



}
