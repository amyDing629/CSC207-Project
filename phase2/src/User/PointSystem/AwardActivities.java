package User.PointSystem;

import Trade.Trade;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * [Controller]
 * Responsible for awarding the users who trade most frequently in one time cycle.
 */
public class AwardActivities {
    Trade currTrade;
    ArrayList<Trade> tradeList;

    Trade getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tradeList.get(tradeNum);
        return currTrade;

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

    //TODO get available trade list
    public void getAvailableList(){

    }

    //TODO The action when user click getBonus button
    public void getBonus(){

    }

}
