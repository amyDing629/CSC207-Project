import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
public class AdministrativeUser extends User {
    private boolean isAdmin;
    private String password;
    private String username;
    private static Integer id;

    private boolean isFrozen;
    private List<String> notification;
    private int weekTransactionLimit;
    private int incompleteTransactionLimit;
    private boolean isBorrow;

    private List<String> wishLend;
    private List<String> wishBorrow;
    private List<Integer> tradeHistory;

    public AdministrativeUser(String username, String password, boolean isAdmin){
        super(username, password, isAdmin);
        id ++;
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
    public boolean getIsAdmin() {
        return false;
    }

    @Override
    public List<String> getWishLend() {
        return null;
    }

    @Override
    public List<String> getWishBorrow() {
        return null;
    }


    public int getIncompleteTransactionLimit() {
        return incompleteTransactionLimit;
    }

    public int getWeekTransactionLimit() {
        return weekTransactionLimit;
    }



    @Override
    public List<Integer> getTradeHistory() {
        return tradeHistory;
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


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean getIsadmin() {
        return false;
    }

    @Override
    public boolean getIsBorrow() {
        return false;
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
    public void changePassword(String password) throws IOException {
        this.password=password;
        UserManager u = new UserManager();
        u.updateFile();
    }

    @Override
    public void changeUsername(String username) throws IOException {
        this.username=username;
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void freeze(ClientUser a) throws IOException {
        a.setIsFrozen(false);
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void unfreeze(ClientUser a) throws IOException {
        a.setIsFrozen(true);
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void addNewUser(String username, String password) throws IOException {
        UserManager u = new UserManager();
        if (id == 0){
        AdministrativeUser a = new AdministrativeUser(username, password,isAdmin);
        u.addUser(a);
        }
        u.updateFile();
    }

    public void confirmItem(ClientUser a, Item b) throws IOException {
        a.addWishLend(b.getName());
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void tradelimit(ClientUser a) throws IOException {
        a.setIsFrozen(a.getTradeNumber() > a.getWeekTransactiionLimit());
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void incompleteTransaction(ClientUser a) throws IOException {
        a.setIsFrozen(a.getIncompleteTransaction() <= a.getIncompleteTransactionLimit());
        UserManager u = new UserManager();
        u.updateFile();
    }

    public void canBorrow(int c, ClientUser b) throws IOException {
        b.setBorrow(b.getLend().size() + c >= b.getBorrowed().size());
        UserManager u = new UserManager();
        u.updateFile();
    }

}
