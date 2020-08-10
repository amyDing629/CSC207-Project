package User.PointSystem;

import Trade.Trade;
import Trade.TradeManager;
import Trade.TradeStatus;
import User.Entity.ClientUser;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import Trade.BorderGUIWithThreeTextArea;
import User.UseCase.UserManager;

/**
 * [Controller]
 * Responsible for awarding the users with bonus trades.
 */
public class AwardActivities {
    TradeManager tm = new TradeManager();
    PointManager pm = new PointManager();
    Trade currTrade;
    ArrayList<Trade> tradeList;
    JFrame fr;
    PointPresenter pp;
    String currUser;
    UserManager um;

    public AwardActivities(String currUser, JFrame fr, BorderGUIWithThreeTextArea tg){
        this.currUser = currUser;
        this.fr = fr;
        pp = new PointPresenter(tg);
        um = new UserManager();

    }

    Trade getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tradeList.get(tradeNum);
        return currTrade;

    }

    boolean checkInput(String num){
        if (!isNum(num)){
            return true;
        }else return Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tradeList.size();
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
        List<Trade> list = tm.getWeekTradeList(user.getUsername()); // get all trades within the most recent seven days
        for (Trade t: list) {
            if (t.getStatus().equals(TradeStatus.incomplete) && !user.getSelectedBonusTrades().contains(t.getId())) {
                result.add(t); // get incomplete trade from the recent trades
            }
        }return result;
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
        if (selected == null){
            pp.notTradeSelected();
        }else{
            user.addBonusTrade(selected.getId());
            this.pm.setUserPoints(user.getId());
        }

    }



    void submitBut(String tradeNum){
        pp.resetInputArea();
        if (checkInput(tradeNum)){
            pp.wrongInput();
        }else{
            currTrade = getCurrTrade(tradeNum);
            pp.presentTradeInfo(currTrade);
            pp.updateSuccess();
        }
    }

    void backBut(){
        fr.setVisible(true);
        pp.closeFrame();
    }

    public void updateBut(){
        pp.updateFrame(getTradesForExchange(um.getUser(currUser)));
        pp.updatePoint(um.getUser(currUser).getBonusPoints());
        noTradeSelected();
        pp.resetCurr();

    }

    public void updatePoint(){
        pp.updatePoint(um.getUser(currUser).getBonusPoints());

    }
    public void noTradeSelected(){
        pp.noTradeCurr();
    }

    public void ebBut(){
        getBonus(um.getUser(currUser), currTrade);
        updateBut();
        pp.changeSuccess();
    }

    public void updateList(){
        pp.updateFrame(getTradesForExchange(um.getUser(currUser)));
    }

}
