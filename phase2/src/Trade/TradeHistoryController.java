package Trade;

import User.ClientUser;
import User.UserManager;

import java.util.List;

public class TradeHistoryController {
    ClientUser currUser;
    TradeManager tm;
    UserManager um;

    public TradeHistoryController(ClientUser currUser, TradeManager tm, UserManager um){
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
    }

    public String getFreUser(){
        String result = "";
        for (String a : um.getFrequentUser(tm, currUser)) {
            result = result + a + "\n";
        }
        if (result.equals("")){
            return "no user";
        }
        return result;
    }

    public String getTradeHistory(){
        String result = "";
        List<Trade> tHis = tm.getTradeHistoryTop(currUser);
        for (int i = 0; i < tHis.size(); i++) {
           result = result + (i + 1) + ". " + tHis.get(i).toString()+"\n";
        }
        if (result.equals("")){
            return "no trade";
        }
        return result;
    }
}
