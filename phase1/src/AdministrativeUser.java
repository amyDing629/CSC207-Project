import java.io.IOException;
import java.util.*;
public class AdministrativeUser extends User {

    public AdministrativeUser(String username, String password, boolean isAdmin){
        super(username, password, isAdmin);
    }



    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public List<UUID> getTradeHistory() {
        return tradeHistory;
    }


    @Override
    public String getUsername() {
        return username;
    }




    public void freeze(ClientUser a) throws IOException {
        a.setIsFrozen(false);
    }

    public void unfreeze(ClientUser a) throws IOException {
        a.setIsFrozen(true);
    }

    public void addNewUser(String username, String password) throws IOException {
        UserManager u = new UserManager();
        List<User> x = u.splitUser(u.readFile());
        if (id.equals(x.get(0).getId())){
        AdministrativeUser a = new AdministrativeUser(username, password, true);
        u.addUser(a);
        }
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
