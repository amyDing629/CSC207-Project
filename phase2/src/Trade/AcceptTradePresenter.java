package Trade;


import java.util.ArrayList;
import java.util.List;

public class AcceptTradePresenter {
    TradeGUI tg;

    public AcceptTradePresenter(TradeGUI tg) {
    }

    public void notTradeSelected(){
        tg.setMsgText("no trade is selected");

    }

    public void agreeTrade(List<Trade> tl){
        String result = "";
        for (int i = 0; i < tl.size(); i++) {
            result = result + i + ". " + tl.get(i).toString() + "\n";
        }
        if (result.equals("")){
            tg.setListText( "no available trade");
        }else{
            tg.setListText(result);
        }
        tg.setMsgText("you have agreed the trade");
        tg.setCurrText("no trade selected");

    }
}
