import java.util.*;
public class ClientUser extends User {
    private String password;
    private String username;
    private int id;

    private boolean isFrozen;
    private List<String> notification;

    //wish list
    private List<String> wishLend;
    private List<String> wishBorrow;

    public ClientUser(String username, String password, int id){
        super(username, password, id);
    }

    //Inventory

    //tradeHistory
    private List<String> tradeHistory;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<String> getNotification() {
        return notification;
    }

    @Override
    public void addNotification(String no) {
          notification.add(no);
    }

    @Override
    public void changePassword(String password) {
         this.password=password;
    }

    @Override
    public void changeUsername(String username) {
        this.username=username;
    }
    public boolean getIsFrozen(){
        return isFrozen;
    }
    public void setIsFrozen(boolean a){isFrozen = a;}

    public List<String> getWishLend() {
        return wishLend;
    }
    public List<String> getWishBorrow() {
        return wishBorrow;
    }

    public List<String> getTradeHistory() {
        List<String> trade=new ArrayList<String>();
        for (int i=0;i<3;i++){
            trade.add(tradeHistory.get(tradeHistory.size()-1-i));
        }
        return trade;
    }
    public void addWishLend(String wish) {
        wishLend.add(wish);
    }
    public void addWishBorrow(String wish) {
        wishBorrow.add(wish);
    }
    public void addTradeHistory(String history) {
        wishBorrow.add(history);
    }
}
