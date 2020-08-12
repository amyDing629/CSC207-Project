package Trade.Adaptor.CompleteTrade;

import Trade.Adaptor.BorderGUI;
import MeetingSystem.Adapter.MPresenter;
import MeetingSystem.Adapter.MainViewPresenter;
import MeetingSystem.Entity.Meeting;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.MeetingActionManager;
import Trade.Entity.Trade;
import Trade.TradeStatus;
import User.Entity.ClientUser;
import User.UseCase.UserManager;
import Trade.UseCase.TradeManager;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.util.regex.Pattern;

public class CTradeController implements Observer {
    UUID currUser;
    TradeManager tm;
    UserManager um;
    UUID currTrade;
    Boolean isFirst;
    CTradePresenter tp;
    BorderGUI tg;

    public CTradeController(String currUser, BorderGUI tg) {

        tm = new TradeManager();
        um = new UserManager();
        tp = new CTradePresenter(tg);
        this.currUser = um.nameToUUID(currUser);
        this.tg = tg;
    }


    boolean checkInput(String num) {
        if (!isNum(num)) {
            return false;
        } else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tm.getIncomplete(currUser).size());
    }

    private boolean isNum(String str) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    UUID getCurrTrade(String num) {
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tm.getIncomplete(currUser).get(tradeNum).getId();
        return currTrade;
    }


    public boolean enterFirst() {
        Trade tr = tm.getTrade(currTrade);
        isFirst = (tr.getSecondMeeting() == null);
        return isFirst;
    }


    @Override
    public void update(Observable o, Object arg) {
        Trade tr = tm.getTrade(currTrade);
        System.out.println("has updated!" + arg);
        UUID mtID = (UUID) arg;
        Meeting mt = new MeetingActionManager().getMeetingWithId(mtID);
        System.out.println("firstMeetingID: " + mt.getID());
        if (isFirst) {
            if (mt.getStatus().equals(MeetingStatus.INCOMPLETE)) {
                Trade trade = tm.popTrade(currTrade);
                trade.setMeeting(mtID);
                tm.addTrade(trade);
            }
            if (mt.getStatus().equals(MeetingStatus.CANCELLED)) {
                Trade trade = tm.popTrade(currTrade);
                trade.setStatus(TradeStatus.cancelled);
                tm.addTrade(trade);

            }
            if (mt.getStatus().equals(MeetingStatus.COMPLETED)) {
                if (tr.getDuration() == -1) {
                    completeTrade();

                } else {
                    Trade trade = tm.popTrade(currTrade);
                    trade.setSecondMeeting(new MeetingActionManager().setUpSecondMeeting(mtID));
                    tm.addTrade(trade);
                }
            }

        } else {
            Meeting smt = new MeetingActionManager().getMeetingWithId(tr.getSecondMeeting());
            if (smt.getStatus().equals(MeetingStatus.COMPLETED)) {
                completeTrade();
            }
        }

    }

    void completeTrade() {
        Trade trade = tm.popTrade(currTrade);
        trade.setStatus(TradeStatus.complete);
        tm.addTrade(trade);
        makeTrade();
    }

    void makeTrade() {
        Trade tr = tm.popTrade(currTrade);
        if (tr.getType().equals("oneway")) {
            ClientUser bor = um.popUser(tr.getUsers().get(0));
            ClientUser lend = um.popUser(tr.getUsers().get(1));
            bor.getWishBorrow().remove(tr.getItemList().get(0));
            lend.getWishLend().remove(tr.getItemList().get(0));
            bor.setBorrowCounter(bor.getBorrowCounter() + 1);
            lend.setLendCounter(bor.getLendCounter() + 1);
            um.addUser(bor);
            um.addUser(lend);
            tm.addTrade(tr);
            System.out.println("bor-borCounter" + um.getUser(tr.getUsers().get(0)).getBorrowCounter());
            System.out.println("lend-lendCounter" + um.getUser(tr.getUsers().get(1)).getLendCounter());
        } else {
            ClientUser u1 = um.popUser(tr.getUsers().get(0));
            ClientUser u2 = um.popUser(tr.getUsers().get(1));
            u1.getWishBorrow().remove(tr.getItemList().get(1));
            u1.getWishLend().remove(tr.getItemList().get(0));
            u2.getWishBorrow().remove(tr.getItemList().get(0));
            u2.getWishLend().remove(tr.getItemList().get(1));
            u1.setBorrowCounter(u1.getBorrowCounter() + 1);
            u1.setLendCounter(u1.getLendCounter() + 1);
            u2.setBorrowCounter(u2.getBorrowCounter() + 1);
            u2.setLendCounter(u2.getLendCounter() + 1);
            um.addUser(u1);
            um.addUser(u2);
            tm.addTrade(tr);
        }
    }

    void closeFrame(){
        tp.closeFrame();
    }

    public void updateBut(){
        tp.updateFrame(tm.getIncomplete(currUser));
    }

    void submitBut(String tradeNum){
        tp.resetInputArea();
        if (!checkInput(tradeNum)){
            tp.wrongInput();
        }else{
            currTrade = getCurrTrade(tradeNum);
            tp.presentTradeInfo(tm.getTrade(currTrade));
            tp.updateSuccess();
        }
    }

    void backBut(JFrame frame){
        frame.setVisible(true);
        tp.closeFrame();
    }

    public void noTradeSelected(){
        tp.noTradeCurr();
    }

    public void checkCurrTrade(Trade trade){
        if (trade == null){
            tp.notTradeSelected();
        }
    }

    public void action(){
        if (currTrade == null){
            tp.notTradeSelected();
        }else{
            Trade tr = tm.getTrade(currTrade);
            ClientUser user = um.getUser(currUser);
            if (enterFirst()) {
                MPresenter mPresenter = new MainViewPresenter(tr.getMeeting(), user.getId(),
                        tr.getUsers(), true, tg.getFrame());
                mPresenter.run();
                mPresenter.addObserver(this);
            }else {
                MPresenter mPresenter = new MainViewPresenter(tr.getSecondMeeting(),
                        user.getId(), tr.getUsers(), false, tg.getFrame());
                mPresenter.run();
                mPresenter.addObserver(this);
            }
            closeFrame();
        }

    }
}
