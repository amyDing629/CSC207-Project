import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
public class AdministrativeUser extends User {
    private boolean isAdmin;
    private static Integer id = 0;

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


    public void setFrozen(boolean frozen) throws IOException {
        isFrozen = frozen;
    }

    @Override
    public boolean getIsAdmin() {
        return isAdmin;
    }

    @Override
    public List<String> getWishLend() {
        return wishLend;
    }

    @Override
    public List<String> getWishBorrow() {
        return wishBorrow;
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


    @Override
    public List<String> getNotification() {
        return notification;
    }



    public void freeze(ClientUser a) throws IOException {
        a.setIsFrozen(false);
    }

    public void unfreeze(ClientUser a) throws IOException {
        a.setIsFrozen(true);
    }

    public void addNewUser(String username, String password) throws IOException {
        UserManager u = new UserManager();
        if (id == 0){
        AdministrativeUser a = new AdministrativeUser(username, password,isAdmin);
        u.addUser(a);
        }
    }

    public void confirmItem(ClientUser a, Item b) throws IOException {
        a.addWishLend(b.getName());

    }

    public void tradeLimit(ClientUser a) throws IOException {
        a.setIsFrozen(a.getTradeNumber() > a.getWeekTransactionLimit());
    }

    public void incompleteTransaction(ClientUser a) throws IOException {
        a.setIsFrozen(a.getIncompleteTransaction() <= a.getIncompleteTransactionLimit());
    }

    public void canBorrow(int c, ClientUser b) throws IOException {
        b.setBorrow(b.getLend().size() + c >= b.getBorrowed().size());
    }
}
