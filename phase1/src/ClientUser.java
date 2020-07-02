import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
public class ClientUser extends User {
    private String password;
    private String username;
    private static Integer id = 0;
    private boolean isAdmin;

    private boolean isFrozen;
    private List<String> notification;
    private int weekTransactionLimit;
    private int incompleteTransactionLimit;

    //wish list
    private List<String> wishLend;
    private List<String> wishBorrow;

    private List<String> lend;
    private List<String> borrowed;

    private boolean isBorrow;

    //Inventory

    //tradeHistory
    private List<Integer> tradeHistory;

    public ClientUser(String username, String password ,Boolean isAdmin){
        super(username, password, isAdmin);
        isBorrow=true;
        id ++;
    }

    public void setBorrow(boolean borrow) {
        isBorrow = borrow;
    }

    public void setId(Integer id) throws IOException {
        this.id = id;
        UserManager u = new UserManager();
        u.updateFile();
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
    public Integer getId() {
        return id;
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

    @Override
    public List<String> getNotification() {
        return notification;
    }

    @Override
    public void addNotification(String no) {
        notification.add(no);
    }

    public void setNotification(List<String> notification) {
        this.notification = notification;
    }

    @Override
    public void changePassword(String password) {
        this.password=password;
    }

    @Override
    public void changeUsername(String username) {
        this.username=username;
    }


    @Override
    public List<Integer> getTradeHistory() {
        return tradeHistory;
    }

    @Override
    public void setFrozen(boolean aTrue) {

    }

    public boolean getIsFrozen(){
        return isFrozen;
    }
    public void setIsFrozen(boolean a) throws IOException {
        isFrozen = a;
        UserManager u = new UserManager();
        u.updateFile();
    }

    public List<String> getWishLend() {
        return wishLend;
    }
    public List<String> getWishBorrow() {
        return wishBorrow;
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

    public void addWishLend(String wish) throws IOException {
        wishLend.add(wish);
        UserManager u = new UserManager();
        u.updateFile();
    }
    public void addWishBorrow(String wish) throws IOException {
        wishBorrow.add(wish);
        UserManager u = new UserManager();
        u.updateFile();
    }
    public void addTradeHistory(String history) throws IOException {
        wishBorrow.add(history);
        UserManager u = new UserManager();
        u.updateFile();
    }
}
