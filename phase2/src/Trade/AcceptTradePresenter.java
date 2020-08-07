package Trade;


import java.util.List;

public class AcceptTradePresenter {
    BorderGUIWithThreeTextArea tg;

    public AcceptTradePresenter(BorderGUIWithThreeTextArea tg) {
        this.tg = tg;
    }

    public void notTradeSelected(){
        tg.setMsgText("no trade is selected");

    }

    public void agreeTrade(List<Trade> tl, boolean agree){
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
        tg.resetInput();
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
            result = result + i + ". " + tradeList.get(i).toString() + "\n";
        }
        if (result.equals("")){
            tg.setMsgText("no available trade");
        }else{
            tg.setListText(result);
        }
    }

    public void noTradeCurr(){
        tg.setCurrText("no trade selected");
    }


}
