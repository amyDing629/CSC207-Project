package Trade;

import Trade.MeetingSystem.Meeting;
import Trade.MeetingSystem.MeetingManager;
import Trade.MeetingSystem.MeetingStatus;
import User.ClientUser;
import User.UserManager;

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

    public CTradeController(ClientUser currUser, TradeManager tm, UserManager um){
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
    }

    String printIncomplete(){
        tradeList = tm.getIncomplete(currUser);
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            result = result + i + ". " + tradeList.get(i).toString() + "\n";
        }
        if (result.equals("")){
            return "no available trade";
        }
        return result;
    }

    boolean checkInput(String num){
        if (!isNum(num)){
            return false;
        }else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tradeList.size());
    }

    private boolean isNum(String str){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    Trade getCurrTrade(String num){
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
        UUID mtID = (UUID)arg;
        Meeting mt = new MeetingManager().getMeetingWithId(mtID);
        System.out.println("firstMeetingID: " + mt.getID());
        if (isFirst){
            if (mt.getStatus().equals(MeetingStatus.INCOMPLETE)){
                currTrade.setMeeting(mtID);
            }
            if (mt.getStatus().equals(MeetingStatus.CANCELLED)){
                tm.cancelTrade(currTrade);
            }
            if (mt.getStatus().equals(MeetingStatus.COMPLETED)){
                if (currTrade.getDuration() == -1){
                    tm.completeTrade(currTrade);
                }
                else{
                    currTrade.setSecondMeeting(new MeetingManager().setUpSecondMeeting(mtID));
                    System.out.println("secondMeetingID: " + currTrade.getSecondMeeting());
                }
            }

        }else{
            Meeting smt = new MeetingManager().getMeetingWithId(currTrade.getSecondMeeting());
            System.out.println("secondMeetingID: "+smt.getID());
            if (smt.getStatus().equals(MeetingStatus.COMPLETED)){
                tm.completeTrade(currTrade);
            }
        }

    }

}
