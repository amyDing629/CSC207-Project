package Trade;

import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class TradeHistoryController {
    UUID currUser;
    TradeManager tm;
    UserManager um;
    TradeHistoryPresenter tp;
    List<Trade> tradeList;
    List<String> userList;
    JFrame fr;

    public TradeHistoryController(UUID currUser, BorderGUIWithThreeTextArea tgp, JFrame fr){
        this.currUser = currUser;
        this.tm = new TradeManager();
        this.um = new UserManager();
        this.fr = fr;
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
        userList = um.getFrequentUser(currUser);
        return userList;
    }


    public void updateTrade(){
        tp.updateTrade(getComplete());
    }

    public void updateUser(){
        tp.updateFreUser(getFreUser());

    }

}
