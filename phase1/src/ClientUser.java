import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
public class ClientUser extends User {
    private boolean isAdmin;

    private boolean isFrozen;
    private int weekTransactionLimit;
    private int incompleteTransactionLimit;


    private List<String> lend;
    private List<String> borrowed;

    private boolean isBorrow;


    public ClientUser(String username, String password ,Boolean isAdmin){
        super(username, password, isAdmin);
        isBorrow=true;
    }

    public int getIncompleteTransactionLimit() {
        return incompleteTransactionLimit;
    }


    public int getWeekTransactionLimit() {
        return weekTransactionLimit;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean getIsBorrow() {
        return isBorrow;
    }



    public List<String> getLend() {
        return lend;
    }

    public List<String> getBorrowed() {
        return borrowed;
    }


    public boolean getIsFrozen(){
        return isFrozen;
    }
    public void setIsFrozen(boolean a){
        isFrozen = a;
    }



    public int getIncompleteTransaction(){
        TradeManager a = new TradeManager();
        int number=0;
        for (UUID i : tradeHistory) {
            if (a.getTrade(i).status.equals("incomplete")) {
                number++;
            }
        }
        return number;
    }

    public int getTradeNumber(){
        TradeManager a = new TradeManager();
        Trade s = a.getTrade(tradeHistory.get(tradeHistory.size() - 1));
        LocalDateTime x  = s.getCreateTime();
        LocalDateTime y = x.minusDays(7);
        int number = 0;
        for (UUID i : tradeHistory){
            if(a.getTrade(i).getCreateTime().isAfter(y) && a.getTrade(i).getCreateTime().isBefore(x)){
                number ++;
            }
        }
        return number;
    }



    public boolean getIsAdmin(){
        return isAdmin;
    }

}
