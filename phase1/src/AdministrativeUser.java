import java.io.IOException;
import java.util.*;
public class AdministrativeUser extends User {

    public AdministrativeUser(String username, String password, boolean isAdmin){
        super(username, password, isAdmin);
    }

    public void freeze(User a) throws IOException {
        a.setFrozen(true);
    }

    public void unfreeze(User a) throws IOException {
        a.setFrozen(false);
    }

    public void addNewUser(String username, String password) throws IOException {
        UserManager u = new UserManager();
        List<User> x = u.splitUser(u.readFile());
        if (id.equals(x.get(0).getId())){
        AdministrativeUser a = new AdministrativeUser(username, password, true);
        u.addUser(a);
        }
    }


    public void tradeLimit(User a) throws IOException {
        a.setFrozen(a.getTradeNumber() > a.getWeekTransactionLimit());
    }

    public void incompleteTransaction(User a) throws IOException {
        a.setFrozen(a.getIncompleteTransaction() <= a.getIncompleteTransactionLimit());
    }

    public void canBorrow(int c, User b) throws IOException {
        b.setBorrow(b.getLend().size() + c >= b.getBorrowed().size());
    }
}
