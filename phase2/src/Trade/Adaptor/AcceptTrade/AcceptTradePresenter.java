package Trade.Adaptor.AcceptTrade;


import MeetingSystem.UseCase.MeetingActionManager;
import MeetingSystem.UseCase.MeetingManager;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.InputAndPresent;
import Trade.Adaptor.iTradePresenter;
import Trade.Entity.Trade;
import User.Entity.ClientUser;
import User.UseCase.UserManager;
import java.util.ArrayList;
import java.util.List;

public class AcceptTradePresenter implements iTradePresenter {
    private final InputAndPresent tg;

    public AcceptTradePresenter(BorderGUI tg) {
        this.tg = tg;
    }

    public void notTradeSelected(){
        tg.setMsgText("no trade is selected");

    }

    @Override
    public void ActionSuccess(List<Trade> tl, boolean agree) {
        agreeTrade(tl, agree);
    }

    private void agreeTrade(List<Trade> tl, boolean agree){
        String result = "";
        for (int i = 0; i < tl.size(); i++) {
            result = result + i + ". " + tl.get(i).toString() + "\n";
        }
        if (result.equals("")){
            tg.setListText( "no available trade");
        }else{
            tg.setListText(result);
        }
        if (agree){
            tg.setMsgText("you have agreed the trade");
        }else{
            tg.setMsgText("you have refused the trade");

        }
        tg.setCurrText("no trade selected");
    }

    public void wrongInput(){
        tg.setMsgText("wrong input");
    }

    public void resetInputArea(){
        tg.setInput("input", "Trade Number");
    }

    public void presentTradeInfo(Trade trade){
        tg.setCurrText(trade.toString());
    }

    public void updateSuccess(){
        tg.setMsgText("Trade info has been updated successfully");
    }

    public void closeFrame(){
        tg.getFrame().setVisible(false);
    }

    public void updateFrame(List<Trade> tradeList){
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            ArrayList<String> users = new ArrayList<>();
            users.add(new UserManager().UUIDToName(tradeList.get(i).getUsers().get(0)));
            users.add(new UserManager().UUIDToName(tradeList.get(i).getUsers().get(1)));
            result = result + i + ". " + "\n" + "Traders: "+ users + "\n" + tradeList.get(i).toString();
            if (tradeList.get(i).getMeeting() != null){
                result = result + "First meeting: "
                        + new MeetingActionManager().getMeetingWithId(tradeList.get(i).getMeeting()).toString() + "\n";
            }
            if (tradeList.get(i).getSecondMeeting() != null){
                result = result + "Second meeting: "
                        + new MeetingActionManager().getMeetingWithId(tradeList.get(i).getSecondMeeting()).toString() + "\n";
            }
        }
        if (result.equals("")){
            tg.setListText("no available trade");
        }else{
            tg.setListText(result);
        }
    }

    public void noTradeCurr(){
        tg.setCurrText("no trade selected");
    }


}
