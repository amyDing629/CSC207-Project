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
    public User(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        //this.id = id;
        this.notification=new ArrayList<String>();
        this.isAdmin = isAdmin;
    }

    public User() {

    }

    public abstract String getPassword();
    public abstract String getUsername();
    public abstract Integer getId();
    public abstract boolean getIsadmin();
    public abstract boolean getIsBorrow();

    public abstract List<String> getNotification();
    public abstract void addNotification(String no);
    public abstract void changePassword(String password);
    public abstract void changeUsername(String username);

    public abstract void setWishLend(ArrayList<String> lineList2);

    public abstract void setWishBorrow(ArrayList<String> lineList3);

    public void setNotification(List<String> notification) {}

    public abstract void setTradeHistory(ArrayList<Integer> lineList5);

    public abstract List<Integer> getTradeHistory();

    public abstract void setFrozen(boolean aTrue);

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
}