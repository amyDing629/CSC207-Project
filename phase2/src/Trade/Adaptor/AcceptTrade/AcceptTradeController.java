package Trade.Adaptor.AcceptTrade;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iTradeController;
import Trade.Adaptor.iTradePresenter;
import Trade.Entity.Trade;
import Trade.TradeStatus;
import Trade.UseCase.TradeManager;
import User.UseCase.UserManager;
import javax.swing.*;
import java.util.UUID;
import java.util.regex.Pattern;

public class AcceptTradeController implements iTradeController {
    private final UUID currUser;
    private final TradeManager tm;
    private UserManager um;
    private UUID currTrade;
    private final iTradePresenter tp;
    private final JFrame frame;

    public AcceptTradeController(String userName, BorderGUI tg, JFrame fr){
        this.tm = new TradeManager();
        this.um = new UserManager();
        tp = new AcceptTradePresenter(tg);
        frame = fr;
        currUser = um.nameToUUID(userName);
    }


    boolean checkInput(String num){
        if (!isNum(num)){
            return false;
        }else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tm.getUnconfirmed(currUser).size());
    }

    public UUID getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tm.getUnconfirmed(currUser).get(tradeNum).getId();
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
                tp.ActionSuccess(tm.getUnconfirmed(currUser), true);
            }else{
                refuseTrade();
                tp.ActionSuccess(tm.getUnconfirmed(currUser), false);
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
        tp.updateFrame(tm.getUnconfirmed(currUser));
        tp.noTradeCurr();
    }

    public void updateList(){
        tp.updateFrame(tm.getUnconfirmed(currUser));
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
