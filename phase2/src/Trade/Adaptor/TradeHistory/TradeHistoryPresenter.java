package Trade.Adaptor.TradeHistory;

import Trade.Adaptor.BorderGUI;
import Trade.Entity.Trade;
import User.UseCase.UserManager;

import java.util.ArrayList;
import java.util.List;

public class TradeHistoryPresenter {

    BorderGUI tgp;

    public TradeHistoryPresenter(BorderGUI tgp) {
        this.tgp = tgp;
    }

    public void closeFrame() {
        tgp.getFrame().setVisible(false);
    }

    public void updateTrade(List<Trade> tradeList) {
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            ArrayList<String> users = new ArrayList<>();
            users.add(new UserManager().UUIDToName(tradeList.get(i).getUsers().get(0)));
            users.add(new UserManager().UUIDToName(tradeList.get(i).getUsers().get(1)));
            result = result + i + ". " + "\n" + "traders: "+ users + "\n" + tradeList.get(i).toString();
        }
        if (result.equals("")){
            tgp.setMsgText("no available trade");
        }else{
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
