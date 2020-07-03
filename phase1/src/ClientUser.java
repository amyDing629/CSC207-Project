import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
public class ClientUser extends User {
    private static Integer id=0;
    private boolean isAdmin;

    private boolean isFrozen;
    private int weekTransactionLimit;
    private int incompleteTransactionLimit;

    //wish list
    private List<String> wishLend;
    private List<String> wishBorrow;

    private List<String> lend;
    private List<String> borrowed;

    private boolean isBorrow;


    public ClientUser(String username, String password ,Boolean isAdmin){
        super(username, password, isAdmin);
        isBorrow=true;
        id ++;
    }

    public int getIncompleteTransactionLimit() {
        return incompleteTransactionLimit;
    }

    @Override
    public void setUsername(String input1) {
        username=input1;
    }

    @Override
    public void setPassword(String input2) {
        password=input2;
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
    public Integer getId() {
        return id;
    }

    @Override
    public boolean getIsBorrow() {
        return isBorrow;
    }


    public void setWishBorrow(List<String> wishBorrow) {
        this.wishBorrow= wishBorrow;
    }


    public List<String> getLend() {
        return lend;
    }

    public List<String> getBorrowed() {
        return borrowed;
    }


    @Override
    public List<Integer> getTradeHistory() {
        return tradeHistory;
    }

    @Override
    public void setFrozen(boolean aTrue){
        isFrozen = aTrue;
    }

    public boolean getIsFrozen(){
        return isFrozen;
    }
    public void setIsFrozen(boolean a){
        isFrozen = a;
    }



    public List<Trade> getTradeHistoryTop() {
        List<Trade> trade=new ArrayList<>();
        TradeManager a = new TradeManager();
        for (int i=0;i<3;i++){
            trade.add(a.getTrade(tradeHistory.get(tradeHistory.size()-1-i)));
        }
        return trade;
    }


    public int getIncompleteTransaction(){
        TradeManager a = new TradeManager();
        int number=0;
        for (Integer i : tradeHistory) {
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
        for (Integer i : tradeHistory){
            if(a.getTrade(i).getCreateTime().isAfter(y) && a.getTrade(i).getCreateTime().isBefore(x)){
                number ++;
            }
        }
        return number;
    }



    public boolean getIsAdmin(){
        return isAdmin;
    }

    public void addWishLend(String wish) {
        wishLend.add(wish);
    }
    public void addWishBorrow(String wish){
        wishBorrow.add(wish);
    }
    public void addTradeHistory(String history){
        wishBorrow.add(history);
    }
}
