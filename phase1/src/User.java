import java.util.*;
abstract class User {
    protected Integer id = 0;
    protected String password;
    protected String username;
    private List<String> notification;
    private boolean isAdmin;
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

    public abstract List<String> getNotification();
    public abstract void addNotification(String no);
    public abstract void changePassword(String password);
    public abstract void changeUsername(String username);

    public abstract void setWishLend(ArrayList<String> lineList2);

    public abstract void setWishBorrow(ArrayList<String> lineList3);

    public abstract void setTradeHistory(ArrayList<Trade> lineList5);

    public abstract List<Trade> getTradeHistory();

    public abstract void setFrozen(boolean aTrue);

    public abstract boolean getIsAdmin();
}