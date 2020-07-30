package Trade;

import User.ClientUser;
import User.UserManager;

import java.util.List;
import java.util.regex.Pattern;

public class CTradeController {
    ClientUser currUser;
    TradeManager tm;
    UserManager um;
    List<Trade> tradeList;
    Trade currTrade;

    public CTradeController(ClientUser currUser, TradeManager tm, UserManager um){
        this.currUser = currUser;
        this.tm = tm;
        this.um = um;
    }

    String printIncomplete(){
        tradeList = tm.getIncomplete(currUser);
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            result = result + i + ". " + tradeList.get(i).toString() + "\n";
        }
        if (result.equals("")){
            return "no available trade";
        }
        return result;
    }

    boolean checkInput(String num){
        if (!isNum(num)){
            return false;
        }else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tradeList.size());
    }

    private boolean isNum(String str){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    Trade getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tradeList.get(tradeNum);
        return currTrade;
    }


}
