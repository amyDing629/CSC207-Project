package User.PointSystem;

import Trade.Adaptor.BorderGUI;
import Trade.Entity.Trade;

import java.util.List;

public class PointPresenter {
    BorderGUI tg;

    public PointPresenter(BorderGUI tg){
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
        tg.setInput("input", "Trade Number");
    }

    public void presentTradeInfo(Trade trade){
        tg.setCurrText(trade.toString());
    }

    public void changeSuccess(){
        tg.setMsgText("The trade has been set to bonus");
    }

    public void updateSuccess(){
        tg.setMsgText("Trade info has been updated successfully");
    }

    public void wrongInput(){
        tg.setMsgText("Wrong input");
    }

    public void pointNotEnough() {tg.setMsgText("Not enough points to exchange for bonus trade");}

    public void noTradeCurr(){
        tg.setCurrText("No trade selected");
    }

    public void notTradeSelected(){
        tg.setMsgText("No trade is selected");
    }

    public void updatePoint(int points){
        tg.setInput("Points", String.valueOf(points));
    }

    void resetCurr(){
        tg.setCurrText("No trade selected");
    }

}
