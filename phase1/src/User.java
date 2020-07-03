import java.io.IOException;
import java.util.*;
abstract class User {
    protected Integer id = 0;
    protected String password;
    protected String username;
    private List<String> notification;
    private boolean isAdmin;
    private List<String> wishLend;
    private List<String> wishBorrow;
    private boolean isBorrow;
    private boolean isFrozen;
    protected List<Integer> tradeHistory;
    private int weekTransactionLimit;
    private int incompleteTransactionLimit;
    public User(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        //this.id = id;
        this.notification=new ArrayList<>();
        this.wishLend=new ArrayList<>();
        this.wishBorrow=new ArrayList<>();
        this.tradeHistory=new ArrayList<>();
        this.isAdmin = isAdmin;
    }

    public abstract String getPassword();
    public abstract String getUsername();
    public abstract Integer getId();
    public abstract boolean getIsBorrow();
    public boolean getIsfrozen(){
        return isFrozen;
    }

    public List<String> getNotification(){return notification;}
    public void changePassword(String password) throws IOException {
        this.password=password;
    }

    public void changeUsername(String username) throws IOException {
        this.username=username;
    }

    public void setIncompleteTransaction(int incompleteTransaction) throws IOException {
        this.incompleteTransactionLimit = incompleteTransaction;
    }

    public void setWeekTransactionLimit(int weekTransaction) throws IOException {

        this.weekTransactionLimit = weekTransaction;
    }


    public void setWishLend(List<String> wishLend) throws IOException {
        this.wishLend = wishLend;
    }

    public void setNotification(List<String> notification) throws IOException {
        this.notification = notification;
    }

    public void setTradeHistory(List<Integer> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

    public abstract List<Integer> getTradeHistory();

    public abstract void setFrozen(boolean aTrue) throws IOException;

    public abstract boolean getIsAdmin();

    public List<String> getWishLend() {
        return wishLend;
    }
    public List<String> getWishBorrow() {
        return wishBorrow;
    }

    public void setId(Integer id) throws IOException {

        this.id = id;
    }

    public void setBorrow(boolean borrow) throws IOException {

        isBorrow = borrow;
    }

    public List<Trade> getAllTrade(){
        TradeManager a = new TradeManager();
        ArrayList<Trade> b = new ArrayList<>();
        for(Integer i: tradeHistory){
            b.add(a.getTrade(i));
        }
        return b;
    }

    public void addNotification(String no) throws IOException {
        notification.add(no);
    }

    public int getWeekTransactionLimit() {
        return weekTransactionLimit;
    }

    public int getIncompleteTransactionLimit() {
        return incompleteTransactionLimit;
    }

    public void setWishBorrow(ArrayList<String> lineList3) throws IOException {
        wishBorrow = lineList3;
    }
}