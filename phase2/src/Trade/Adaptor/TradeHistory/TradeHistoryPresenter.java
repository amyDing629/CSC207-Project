package Trade.Adaptor.TradeHistory;

import Trade.Adaptor.BorderGUIWithThreeTextArea;
import Trade.Entity.Trade;

import java.util.List;

public class TradeHistoryPresenter {

    BorderGUIWithThreeTextArea tgp;

    public TradeHistoryPresenter(BorderGUIWithThreeTextArea tgp) {
        this.tgp = tgp;
    }

    public void closeFrame() {
        tgp.getFrame().setVisible(false);
    }

    public void updateTrade(List<Trade> tradeList) {
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            result = result + i + ". " + tradeList.get(i).toString() + "\n";
        }
        if (result.equals("")) {
            tgp.setListText("No Available Trade");
        } else {
            tgp.setListText(result);
        }
    }

    public void updateFreUser(List<String> userList) {
        String result = "";
        for (String a : userList) {
            result = result + a + "\n";
        }
        if (result.equals("")) {
            tgp.setCurrText("No User");
        } else {
            tgp.setCurrText(result);
        }
    }
}
