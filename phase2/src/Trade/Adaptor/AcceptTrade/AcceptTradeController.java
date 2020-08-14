package Trade.Adaptor.AcceptTrade;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iTradeController;
import Trade.Entity.Trade;
import Trade.TradeStatus;
import Trade.UseCase.TradeManager;
import User.UseCase.UserManager;
import javax.swing.*;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class AcceptTradeController implements iTradeController {
    UUID currUser;
    TradeManager tm;
    UserManager um;
    List<Trade> tradeList;
    UUID currTrade;
    AcceptTradePresenter tp;
    JFrame frame;

    public AcceptTradeController(String userName, BorderGUI tg, JFrame fr){
        this.tm = new TradeManager();
        this.um = new UserManager();
        tp = new AcceptTradePresenter(tg);
        frame = fr;
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

    public UUID getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tradeList.get(tradeNum).getId();
        return currTrade;

    }

    private boolean isNum(String str){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    void agreeTrade(){
        Trade trade = tm.popTrade(currTrade);
        trade.setStatus(TradeStatus.incomplete);
        tm.addTrade(trade);
    }

    void refuseTrade(){
        Trade trade = tm.popTrade(currTrade);
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

    public void submitBut(String tradeNum){
        if (!checkInput(tradeNum)){
            tp.wrongInput();
        }else{
            currTrade = getCurrTrade(tradeNum);
            tp.presentTradeInfo(tm.getTrade(currTrade));
            tp.updateSuccess();
        }
    }

    public void backBut(){
        frame.setVisible(true);
        tp.closeFrame();
    }

    public void updateBut(){
        tp.updateFrame(getUnconfirmed());
        tp.noTradeCurr();
    }

    public void updateList(){
        tp.updateFrame(getUnconfirmed());
    }

    public void noTradeSelected(){
        tp.noTradeCurr();
    }

    @Override
    public void performAction1() {
        agreeBut(true);
    }

    @Override
    public void performAction2() {
        agreeBut(false);
    }


}
