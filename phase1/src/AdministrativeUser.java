import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
public class AdministrativeUser extends User {
    private boolean isAdmin;
    private String password;
    private String username;
    private  Integer id;

    private boolean isFrozen;
    private List<String> notification;
    private int weekTransactionLimit;
    private int incompleteTransactionLimit;
    private boolean isBorrow;

    private List<String> wishLend;
    private List<String> wishBorrow;
    private List<Trade> tradeHistory;

    public AdministrativeUser(String username, String password,List<String> notification,boolean isAdmin){
        super(username, password, notification, isAdmin);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    @Override
    public void setWishLend(ArrayList<String> lineList2) {

    }

    @Override
    public void setWishBorrow(ArrayList<String> lineList3) {

    }

    @Override
    public void setTradeHistory(ArrayList<Trade> lineList5) {

    }

    public void setBorrow(boolean borrow) {
        isBorrow = borrow;
    }

    public void setWishBorrow(List<String> wishBorrow) {
        this.wishBorrow = wishBorrow;
    }

    public void setWishLend(List<String> wishLend) {
        this.wishLend = wishLend;
    }

    public void setTradeHistory(List<Trade> tradeHistory) {
        this.tradeHistory = tradeHistory;
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

    public void freeze(ClientUser a){a.setIsFrozen(false);}

    public void unfreeze(ClientUser a){a.setIsFrozen(true);}

    public void addNewUser(String username, String password){
        if (id == 0){
        AdministrativeUser a = new AdministrativeUser(username, password,notification,isAdmin);}
    }

    public void confirmItem(ClientUser a, Item b){
        a.addWishLend(b.getName());
    }

    public void tradelimit(ClientUser a){
        a.setIsFrozen(a.getTradeNumber() > a.getWeekTransactiionLimit());
    }

    public void incompleteTransaction(ClientUser a){
        a.setIsFrozen(a.getIncompleteTransaction() <= a.getIncompleteTransactionLimit());
    }

    public void canBorrow(int c, ClientUser b){
        b.setBorrow(b.getLend().size() + c >= b.getBorrowed().size());
    }

}
