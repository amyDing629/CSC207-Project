import java.util.ArrayList;

/**
 * store trade
 * set up trade
 * cancel trade
 */
public class TradeManager {
    ArrayList<Trade> tradeList;


    /**
     * constructor, tradeList is an instance variable that stores all the Trade.
     */
    public TradeManager(){
        tradeList = new ArrayList<Trade>();
    }


    /**
     * if the User wants to make a one way trade, tradeManager will create a one way trade
     * @param lender
     * @param borrower
     * @param item
     * @param duration
     * @return
     */
    public Trade createOnewayTrade(ClientUser lender, ClientUser borrower, Item item, int duration){
        OnewayTrade newTrade = new OnewayTrade(lender, borrower, item, duration);
        tradeList.add(newTrade);
        return newTrade;
    }

    /**
     * if the User wants to make a two way trade, tradeManager will create a two way trade.
     * @param user1
     * @param user2
     * @param item1to2
     * @param item2to1
     * @param duration
     * @return
     */
    public Trade createTwowayTrade(ClientUser user1, ClientUser user2, Item item1to2, Item item2to1, int duration){
        TwowayTrade newTrade = new TwowayTrade(user1, user2, item1to2, item2to1, duration);
        tradeList.add(newTrade);
        return newTrade;
    }


    public Trade getTrade(int id) {
        for (Trade trade : tradeList) {
            if (trade.getId() == id) {
                return trade;
            } else {
                System.out.println("no id existed"); // need to change to raise XXX error.
            }
        }
    }

    /**
     * abstract class
     * make trade happen, move items in User's list
     */
   // public void makeTrade(){

    //};



}
