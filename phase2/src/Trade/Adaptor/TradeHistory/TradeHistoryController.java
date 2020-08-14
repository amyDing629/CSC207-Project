package Trade.Adaptor.TradeHistory;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iTradeController;
import Trade.Entity.Trade;
import User.UseCase.UserManager;
import Trade.UseCase.TradeManager;
import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class TradeHistoryController implements iTradeController {
    UUID currUser;
    TradeManager tm;
    UserManager um;
    iTradeHistoryPresenter tp;
    List<Trade> tradeList;
    List<String> userList;
    JFrame fr;

    public TradeHistoryController(String currUser, BorderGUI tgp, JFrame fr){
        this.tm = new TradeManager();
        this.um = new UserManager();
        tp = new TradeHistoryPresenter(tgp);
        this.currUser = um.nameToUUID(currUser);
        this.fr = fr;

    }


    public String getTradeHistory(){
        String result = "";
        List<Trade> tHis = tm.getComplete(currUser);
        for (int i = 0; i < tHis.size(); i++) {
           result = result + (i + 1) + ". " + tHis.get(i).toString()+"\n";
        }
        if (result.equals("")){
            return "no trade";
        }
        return result;
    }

    @Override
    public UUID getCurrTrade(String num) {
        return null;
    }


    public void backBut(){
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

    public void updateBut(){
        updateTrade();
        updateUser();
    }

    @Override
    public void noTradeSelected() {
    }

    @Override
    public void performAction1() {
    }

    @Override
    public void performAction2() {
    }

    @Override
    public void submitBut(String tradeNum){
    }

    public void updateList(){
        updateTrade();
    }

}
