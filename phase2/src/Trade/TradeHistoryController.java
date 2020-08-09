package Trade;

import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.List;

public class TradeHistoryController {
    ClientUser currUser;
    TradeManager tm;
    UserManager um;
    TradeHistoryPresenter tp;
    List<Trade> tradeList;
    List<String> userList;
    JFrame fr;

    public TradeHistoryController(ClientUser currUser, TradeManager tm, UserManager um, BorderGUIWithThreeTextArea tgp,
                                  JFrame fr){
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
        tp = new TradeHistoryPresenter(tgp);

    }



    public String getTradeHistory(){
        String result = "";
        List<Trade> tHis = tm.getTradeHistoryTop(currUser);
        for (int i = 0; i < tHis.size(); i++) {
           result = result + (i + 1) + ". " + tHis.get(i).toString()+"\n";
        }
        if (result.equals("")){
            return "no trade";
        }
        return result;
    }

    void backBut(){
        fr.setVisible(true);
        tp.closeFrame();
    }

    List<Trade> getComplete() {
        tradeList = tm.getComplete(currUser);
        return tradeList;
    }

    List<String> getFreUser() {
        userList = um.getFrequentUser(tm, currUser);
        return userList;
    }



    public void updateTrade(){
        tp.updateTrade(getComplete());

    }

    public void updateUser(){
        tp.updateFreUser(getFreUser());

    }
}
