package Trade.Adaptor.AcceptTrade;

import Trade.Adaptor.BorderGUIWithThreeTextArea;
import User.UseCase.UserManager;
import Trade.Entity.Trade;
import javax.swing.*;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import Trade.UseCase.TradeManager;
import Trade.TradeStatus;

public class AcceptTradeController {
    UUID currUser;
    TradeManager tm;
    UserManager um;
    List<Trade> tradeList;
    Trade currTrade;
    AcceptTradePresenter tp;

    public AcceptTradeController(String userName, BorderGUIWithThreeTextArea tg){
        this.tm = new TradeManager();
        this.um = new UserManager();
        tp = new AcceptTradePresenter(tg);
        currUser = um.nameToUUID(userName);
    }

    List<Trade> getUnconfirmed(){
        tradeList = tm.getUnconfirmed(currUser);
        return tradeList;
    }

    boolean checkInput(String num){
        if (!isNum(num)){
            return false;
        }else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tradeList.size());
    }

    Trade getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tradeList.get(tradeNum);
        return currTrade;

    }

    private boolean isNum(String str){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    void agreeTrade(){
        Trade trade = tm.popTrade(currTrade.getId());
        trade.setStatus(TradeStatus.incomplete);
        tm.addTrade(trade);
    }

    void refuseTrade(){
        Trade trade = tm.popTrade(currTrade.getId());
        trade.setStatus(TradeStatus.cancelled);
        tm.addTrade(trade);
    }

    void agreeBut(Boolean agree){
        if (currTrade == null){
            tp.notTradeSelected();
        } else{
            if (agree){
                agreeTrade();
                tp.agreeTrade(tm.getUnconfirmed(currUser), true);
            }else{
                refuseTrade();
                tp.agreeTrade(tm.getUnconfirmed(currUser), false);
            }
            currTrade = null;

        }
    }

    void submitBut(String tradeNum){
        if (!checkInput(tradeNum)){
            tp.wrongInput();
        }else{
            currTrade = getCurrTrade(tradeNum);
            tp.presentTradeInfo(currTrade);
            tp.updateSuccess();
        }
    }

    void backBut(JFrame frame){
        frame.setVisible(true);
        tp.closeFrame();
    }

    public void updateBut(){
        tp.updateFrame(getUnconfirmed());

    }

    public void noTradeSelected(){
        tp.noTradeCurr();
    }


}
