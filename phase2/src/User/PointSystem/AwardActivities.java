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

    //TODO:get available trade list
    /**
     * Provide a list of user's incomplete recent trades for user to select as a bonus trade by exchanging bonus points
     */
    public List<Trade> getTradesForExchange(ClientUser user){
        List<Trade> result = new ArrayList<Trade>();
        List<Trade> list = this.tm.getWeekTradeList(user); // get all trades within the most recent seven days
        for (Trade t: list) {
            if (t.getStatus().equals(TradeStatus.incomplete)) {
                result.add(t); // get incomplete trade from the recent trades
            }
        }return result;
    }

    //TODO: The action when user click getBonus button
    /**
     * Note: when the user click on getBonus button,
     * 1. the tradeId is added into user's bonusTradeList (this Trade should not be removed from tradeHistory)
     * 2. the bonus point is reduced by one exStandard ***
     * 3. update the PointManager.pointList
     * 4. update the ClientUser.bonusPoints
     * 2, 3, 4 should all be done by PointManager.updatePoints
     */
    public void getBonus(){

    }

}
