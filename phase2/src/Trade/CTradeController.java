package Trade;

import Trade.MeetingSystem.Adapter.MPresenter;
import Trade.MeetingSystem.Adapter.MainViewPresenter;
import Trade.MeetingSystem.Entity.Meeting;
import Trade.MeetingSystem.MeetingStatus;
import Trade.MeetingSystem.UseCase.MeetingActionManager;
import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.util.regex.Pattern;

public class CTradeController implements Observer {
    ClientUser currUser;
    TradeManager tm;
    UserManager um;
    List<Trade> tradeList;
    Trade currTrade;
    Boolean isFirst;
    CTradePresenter tp;
    BorderGUIWithThreeTextArea tg;

    public CTradeController(ClientUser currUser, TradeManager tm, UserManager um, BorderGUIWithThreeTextArea tg) {
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
        tp = new CTradePresenter(tg);
        this.tg = tg;
    }

    List<Trade> getIncomplete() {
        tradeList = tm.getIncomplete(currUser);
        return tradeList;
    }

    boolean checkInput(String num) {
        if (!isNum(num)) {
            return false;
        } else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tradeList.size());
    }

    private boolean isNum(String str) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    Trade getCurrTrade(String num) {
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tradeList.get(tradeNum);
        return currTrade;
    }


    public boolean enterFirst() {
        isFirst = (currTrade.getSecondMeeting() == null);
        return isFirst;
    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("has updated!" + arg);
        UUID mtID = (UUID) arg;
        Meeting mt = new MeetingActionManager().getMeetingWithId(mtID);
        System.out.println("firstMeetingID: " + mt.getID());
        if (isFirst) {
            if (mt.getStatus().equals(MeetingStatus.INCOMPLETE)) {
                currTrade.setMeeting(mtID);
            }
            if (mt.getStatus().equals(MeetingStatus.CANCELLED)) {
                tm.cancelTrade(currTrade);
            }
            if (mt.getStatus().equals(MeetingStatus.COMPLETED)) {
                if (currTrade.getDuration() == -1) {
                    tm.completeTrade(currTrade);
                } else {
                    currTrade.setSecondMeeting(new MeetingActionManager().setUpSecondMeeting(mtID));
                    System.out.println("secondMeetingID: " + currTrade.getSecondMeeting());
                }
            }

        } else {
            Meeting smt = new MeetingActionManager().getMeetingWithId(currTrade.getSecondMeeting());
            System.out.println("secondMeetingID: " + smt.getID());
            if (smt.getStatus().equals(MeetingStatus.COMPLETED)) {
                tm.completeTrade(currTrade);
            }
        }

    }
    void completeTrade(){
        makeTrade();
        tm.completeTrade(currTrade);
    }

    void makeTrade() {
        if (currTrade.getType().equals("oneway")) {
            ClientUser bor = um.getUser(currTrade.getUsers().get(0));
            ClientUser lend = um.getUser(currTrade.getUsers().get(1));
            System.out.println(bor);
            System.out.println(bor.getWishBorrow());
            System.out.println(currTrade.getItemList());
            System.out.println(currTrade.getItemList().get(0).getName());
            bor.getWishBorrow().remove(currTrade.getItemList().get(0).getName());
            lend.getWishLend().remove(currTrade.getItemList().get(0).getName());
            bor.setBorrowCounter(bor.getBorrowCounter() + 1);
            bor.setLendCounter(bor.getLendCounter() + 1);


        } else {
            ClientUser u1 = um.getUser(currTrade.getUsers().get(0));
            ClientUser u2 = um.getUser(currTrade.getUsers().get(1));
            u1.getWishBorrow().remove(currTrade.getItemList().get(1).getName());
            u1.getWishLend().remove(currTrade.getItemList().get(0).getName());
            u2.getWishBorrow().remove(currTrade.getItemList().get(0).getName());
            u2.getWishLend().remove(currTrade.getItemList().get(1).getName());
            u1.setBorrowCounter(u1.getBorrowCounter() + 1);
            u1.setLendCounter(u1.getLendCounter() + 1);
            u2.setBorrowCounter(u2.getBorrowCounter() + 1);
            u2.setLendCounter(u2.getLendCounter() + 1);
        }
    }

    void closeFrame(){
        tp.closeFrame();
    }

    public void updateBut(){
        tp.updateFrame(getIncomplete());

    }

    void submitBut(String tradeNum){
        tp.resetInputArea();
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

    public void noTradeSelected(){
        tp.noTradeCurr();
    }

    public void checkCurrTrade(Trade trade){
        if (trade == null){
            tp.notTradeSelected();
        }
    }

    public void action(){
        if (enterFirst()) {
            MPresenter mPresenter = new MainViewPresenter(currTrade.getMeeting(), currUser.getId(),
                    currTrade.getUsers(), true, tg.getFrame());
            mPresenter.run();
            mPresenter.addObserver(this);
        }else {
            MPresenter mPresenter = new MainViewPresenter(currTrade.getSecondMeeting(),
                    currUser.getId(), currTrade.getUsers(), false, tg.getFrame());
            mPresenter.run();
            mPresenter.addObserver(this);
        }
    }
}
