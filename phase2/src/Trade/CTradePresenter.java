package Trade;

import Trade.MeetingSystem.Gui.MPresenter;
import Trade.MeetingSystem.Gui.MainViewPresenter;

import java.util.List;

public class CTradePresenter {
    TradeGUI tg;

    public CTradePresenter(TradeGUI tg) {
        this.tg = tg;
    }

    public void closeFrame(){
        tg.getFrame().setVisible(false);

    }

    public void updateFrame(List<Trade> tradeList){
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            result = result + i + ". " + tradeList.get(i).toString() + "\n";
        }
        if (result.equals("")){
            tg.setMsgText("no available trade");
        }else{
            tg.setListText(result);
        }
    }

    public void resetInputArea(){
        tg.resetInput();
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