package User;

import Main.GateWay;
import Trade.TradeManager;

import java.io.IOException;
import java.util.*;
/**
 * [Entity class]
 * The basic functionality of administrative users
 */
public class AdministrativeUser extends User {
    TradeManager tm;
    UserManager um;

    /**
     * @param username the username of the user account
     * @param password the password of the user account
     * @param isAdmin the boolean shows if the user is administrative user or not
     * ID is random generated and is unique
     */
    public AdministrativeUser(String username, String password, boolean isAdmin, TradeManager tm, UserManager um){
        super(username, password, isAdmin);
        this.tm = tm;
        this.um = um;
    }

    /**
     * @param a the user that the administrative user wants to set frozen
     * set the user.User a account frozen
     */
    public void freeze(User a) {
        a.setFrozen(true);
    }

    /**
     * @param a the user that the administrative user wants to set not frozen
     * set the user.User a account unfrozen
     */
    public void unfreeze(User a){
        a.setFrozen(false);
    }

    /**
     * @param username the name of the administrative user that wants to add
     * @param password the password of the administrative user that wants to add
     * the initial administrative user can add the new administrative user
     */
    public void addNewUser(String username, String password) throws IOException {
        List<User> x = um.getUserList();
        if (id.equals(x.get(0).getId())){
        AdministrativeUser a = new AdministrativeUser(username, password, true, tm, um);
        x.add(a);
        }
    }

    /**
     * @param a the user that the administrative user wants to check the transaction limit
     * set the user.User a account frozen a has exceeded the week transaction limit
     */
    public void tradeLimit(User a) throws IOException {
        a.setFrozen(tm.getTradeNumber(a) > a.getWeekTransactionLimit());
    }

    /**
     * @param a the user that the administrative user wants to check the incomplete transaction limit
     * set the user.User a account frozen if a has exceeded the incomplete transaction limit
     */
    public void incompleteTransaction(User a) throws IOException {
        a.setFrozen(tm.getIncompleteTransaction(a) <= a.getIncompleteTransactionLimit());
    }

    /**
     * @param c the difference between the amount of user.User b borrows and lends
     * @param b the user that the administrative user wants to check the difference between the amount of user.User b
     *          borrows and lends
     *set the user.User a account isBorrow false if b does not lend more than c items of the amount that user.User b borrows
     */
    public void canBorrow(int c, User b) throws IOException {
        b.setBorrow(b.getLend().size() + c >= b.getBorrowed().size());
        b.setDiff(c);
    }
}
