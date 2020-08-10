package User.PointSystem;

import Trade.BorderGUIWithThreeTextArea;
import Trade.Trade;

import java.util.List;

public class PointPresenter {
    BorderGUIWithThreeTextArea tg;

    public PointPresenter(BorderGUIWithThreeTextArea tg){
        this.tg = tg;
    }

    public void closeFrame(){
        tg.getFrame().setVisible(false);

    }

    public void updateFrame(List<Trade> tradeList){
        StringBuilder results = new StringBuilder();
        for (Trade t: tradeList) {
            String trade = "Trade{" +
                    "tradeId=" + t.getId() +
                    ", user1=" + t.getUsers().get(0) +
                    ", user2=" + t.getUsers().get(1) +
                    ", create at=" + t.getCreateTime() +
                    "} " + "\n";
            results.append(trade);
        }
        tg.setListText(results.toString());
    }

    public void resetInputArea(){
        tg.setInput("input", "trade number");
    }

    public void presentTradeInfo(Trade trade){
        tg.setCurrText(trade.toString());
    }

    public void changeSuccess(){
        tg.setMsgText("the trade has been set to bonus");
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

    public void updatePoint(int points){
        tg.setInput("points", String.valueOf(points));
    }

    void resetCurr(){
        tg.setCurrText("no trade selected");
    }

}
