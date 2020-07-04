import java.io.IOException;
import java.util.*;
public class AdministrativeUser extends User {
    private static Integer id = 0;

    public AdministrativeUser(String username, String password, boolean isAdmin){
        super(username, password, isAdmin);
        id ++;
    }



    @Override
    public String getPassword() {
        return password;
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



    public void freeze(ClientUser a) throws IOException {
        a.setIsFrozen(false);
    }

    public void unfreeze(ClientUser a) throws IOException {
        a.setIsFrozen(true);
    }

    public void addNewUser(String username, String password) throws IOException {
        UserManager u = new UserManager();
        if (id == 1){
        AdministrativeUser a = new AdministrativeUser(username, password, true);
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
