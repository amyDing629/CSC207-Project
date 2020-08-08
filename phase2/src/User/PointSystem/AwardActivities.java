package User.PointSystem;

import Trade.Trade;
import Trade.TradeManager;
import Trade.TradeStatus;
import User.ClientUser;

import java.util.*;
import java.util.regex.Pattern;

/**
 * [Controller]
 * Responsible for awarding the users with bonus trades.
 */
public class AwardActivities {
    TradeManager tm;
    PointManager pm;
    Trade currTrade;
    ArrayList<Trade> tradeList;

    Trade getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tradeList.get(tradeNum);
        return currTrade;

    }

    boolean checkInput(String num){
        if (!isNum(num)){
            return false;
        }else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tradeList.size());
    }

    private boolean isNum(String str){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * Provide a list of trades for user to select as bonus trades to avoid counting towards being frozen.
     * The trades in list are incomplete trades within the most recent 7 days.
     * Once the trade is selected as bonus, a fixed amount of bonus points will be deducted.
     * @param user the current user who makes actions
     */
    public List<Trade> getTradesForExchange(ClientUser user){
        List<Trade> result = new ArrayList<Trade>();
        List<Trade> list = this.tm.getWeekTradeList(user); // get all trades within the most recent seven days
        for (Trade t: list) {
            if (t.getStatus().equals(TradeStatus.incomplete) && !user.getSelectedBonusTrades().contains(t.getId())) {
                result.add(t); // get incomplete trade from the recent trades
            }
        }return result;
    }

    /**
     * Based on the ClientUser's available points, indicate the max number of trades that can be selected as bonus
     */
    public int canSelectUpTo(ClientUser user) {
        return user.getBonusPoints() / this.pm.getExStandard();
    }

    /**
     * When the user click on getBonus button,
     * 1. the tradeId is added into user's bonusTradeList (this Trade will not be removed from tradeHistory)
     * 2. the bonus point is reduced by a fixed amount
     * 3. update the PointManager.pointList
     * 4. update the ClientUser.bonusPoints
     * @param user the current user who is making actions
     * @param selected the trade user selected to be bonus
     */
    public void getBonus(ClientUser user, Trade selected){
            user.addBonusTrade(selected.getId());
        this.pm.setUserPoints(user);
    }

    /**
     * Return the string representation of trades to exchange
     */
    public String toString(List<Trade> tradeList) {
        StringBuilder results = new StringBuilder();
        for (Trade t: tradeList) {
            String trade = "Trade{" +
                    "tradeId=" + t.getId() +
                    ", user1=" + t.getUsers().get(0) +
                    ", user2=" + t.getUsers().get(1) +
                    ", create at=" + t.getCreateTime() +
                    "} ";
            results.append(trade);
        }
        return results.toString();
    }

}
