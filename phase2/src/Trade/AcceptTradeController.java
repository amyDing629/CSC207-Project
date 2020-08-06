package Trade;

import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.util.List;
import java.util.regex.Pattern;

public class AcceptTradeController {
    ClientUser currUser;
    TradeManager tm;
    UserManager um;
    List<Trade> tradeList;
    Trade currTrade;
    AcceptTradePresenter tp;

    public AcceptTradeController(ClientUser currUser, TradeManager tm, UserManager um, TradeGUI tg){
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
        tp = new AcceptTradePresenter(tg);
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
        TradeEditor te = new TradeEditor();
        te.setStatus(currTrade, TradeStatus.incomplete);
    }

    void refuseTrade(){
        TradeEditor te = new TradeEditor();
        te.setStatus(currTrade, TradeStatus.cancelled);
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
        tp.resetInputArea();
        if (!checkInput(tradeNum)){
            tp.wrongInput();;
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
