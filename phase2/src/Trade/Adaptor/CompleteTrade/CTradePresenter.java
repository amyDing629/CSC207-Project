package Trade.Adaptor.CompleteTrade;

import Trade.Adaptor.BorderGUI;
import Trade.Entity.Trade;
import User.UseCase.UserManager;

import java.util.ArrayList;
import java.util.List;

public class CTradePresenter {
    BorderGUI tg;

    public CTradePresenter(BorderGUI tg) {
        this.tg = tg;
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
            result = result + i + ". " + "\n" + "traders: "+ users + "\n" + tradeList.get(i).toString();
        }
        if (result.equals("")){
            tg.setMsgText("no available trade");
        }else{
            tg.setListText(result);
        }
    }

    public void resetInputArea(){
        tg.setInput("input", "item name");
    }

    public void presentTradeInfo(Trade trade){
        tg.setCurrText(trade.toString());
    }

    public void updateSuccess(){
        tg.setMsgText("Trade info has been updated successfully");
    }

    public void wrongInput(){
        tg.setMsgText("wrong input");
    }

    public void noTradeCurr(){
        tg.setCurrText("no trade selected");
    }

    public void notTradeSelected(){
        tg.setMsgText("no trade is selected");
    }


}
