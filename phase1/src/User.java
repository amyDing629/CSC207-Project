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
    private List<Integer> tradeHistory;
    private int weekTransactionLimit;
    private int incompleteTransactionLimit;
    public User(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        //this.id = id;
        this.notification=new ArrayList<String>();
        this.isAdmin = isAdmin;
    }

    public abstract String getPassword();
    public abstract String getUsername();
    public abstract Integer getId();
    public abstract boolean getIsBorrow();

    public abstract List<String> getNotification();
    public abstract void addNotification(String no) throws IOException;
    public abstract void changePassword(String password) throws IOException;
    public abstract void changeUsername(String username) throws IOException;

    public void setIncompleteTransaction(int incompleteTransaction) throws IOException {
        this.incompleteTransactionLimit = incompleteTransaction;
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void setWeekTransactionLimit(int weekTransaction) throws IOException {

        this.weekTransactionLimit = weekTransaction;
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void setWishBorrow(List<String> wishBorrow) throws IOException {
        this.wishBorrow = wishBorrow;
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void setWishLend(List<String> wishLend) throws IOException {
        this.wishLend = wishLend;
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void setNotification(List<String> notification) throws IOException {}

    public void setTradeHistory(List<Integer> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

    public abstract List<Integer> getTradeHistory();

    public abstract void setFrozen(boolean aTrue) throws IOException;

    public abstract boolean getIsAdmin();

    public abstract List<String> getWishLend();
    public abstract List<String> getWishBorrow();

    public List<Trade> getAllTrade(){
        TradeManager a = new TradeManager();
        ArrayList<Trade> b = new ArrayList<>();
        for(Integer i: tradeHistory){
            b.add(a.getTrade(i));
        }
        return b;
    }

    public int getWeekTransactionLimit() {
        return weekTransactionLimit;
    }

    public int getIncompleteTransactionLimit() {
        return incompleteTransactionLimit;
    }
}