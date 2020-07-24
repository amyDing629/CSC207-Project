package User;

import Trade.TradeManager;

import java.util.List;
import java.util.UUID;

/**
 * This is the use case class of administrative user
 * Administrative user are allowed to
 */

public class AdminActivityManager {
    private TradeManager tm;
    private UserManager um;

    public AdminActivityManager(TradeManager tm, UserManager um) {
        this.tm = tm;
        this.um = um;
    }

    /**
     * @param a the user that the administrative user wants to set frozen
     * set the user.ClientUser a account frozen
     */
    public void setFreeze(ClientUser a,boolean s) {
        a.setFrozen(s);
    }

    /**
     * @param username the name of the administrative user that wants to add
     * @param password the password of the administrative user that wants to add
     * the initial administrative user can add the new administrative user
     */
    public void addNewAdmin(ClientUser user,String username, String password) {
        List<ClientUser> userList = um.getUserList();
        if(user.getUsername().equals("admin")) {
            ClientUser admin = new ClientUser(username, password, true);
            userList.add(admin);
        }
    }

    /**
     * Change thresholds for a particular user upon selection
     * @param username the username of this particular user
     * @param newLimit the new threshold that the admin wants to adjust to
     * @param whichToChange indicates the threshold to adjust
     */
    public void AdjustThreshold(String username, int newLimit, int whichToChange) {
        List<ClientUser> userList = um.getUserList();
        for (ClientUser u: userList) {
            if (u.getUsername().equals(username)) {
                if (whichToChange == 1) {
                    u.setDiff(newLimit);
                }if (whichToChange == 2) {
                    u.setWeekTransactionLimit(newLimit);
                }if (whichToChange == 3) {
                    u.setIncompleteTransaction(newLimit);
                }
            }
        }
    }

    /**
     * Adjust the limit of lent-borrow difference threshold of a particular user
     * @param username the username of the user
     * @param newDiff the new threshold that the admin wants to adjust to
     */
    public void AdjustDiffForUser(String username, int newDiff) {
        List<ClientUser> userList = um.getUserList();
        for (ClientUser u: userList) {
            if (u.getUsername().equals(username)) {
                u.setDiff(newDiff);
            }
        }
    }

    /**
     * Adjust the limit of lent-borrow difference threshold of a particular user
     * @param newDiff the new threshold that the admin wants to adjust to
     */
    public void AdjustDiffForAll(int newDiff) {
        List<ClientUser> userList = um.getUserList();
        for (ClientUser u: userList) {
            u.setDiff(newDiff);
        }
    }

    /**
     * Change the weekly transaction limit for a particular user
     * @param newLimit the new threshold that the admin wants to adjust to
     */
    public void AdjustWeeklyLimit(String username, int newLimit) {
        List<ClientUser> userList = um.getUserList();
        for (ClientUser u: userList) {
            if (u.getUsername().equals(username)) {
                u.setWeekTransactionLimit(newLimit);
            }
        }
    }

    /**
     * Change the incomplete transaction limit for a particular user
     * @param newLimit the new threshold that the admin wants to adjust to
     */
    public void AdjustIncompleteLimit(String username, int newLimit) {
        List<ClientUser> userList = um.getUserList();
        for (ClientUser u: userList) {
            if (u.getUsername().equals(username)) {
                u.setIncompleteTransaction(newLimit);
            }
        }
    }

    /**
     * @param a the user that the administrative user wants to check the incomplete transaction limit
     * set the account of user a frozen if a has exceeded the incomplete transaction limit
     */
    public boolean incompleteTransaction(ClientUser a){
        if(!a.getIsFrozen()){
            a.setFrozen(tm.getIncompleteTransaction(a) > a.getIncompleteTransactionLimit());
            return true;
        }
        return false;
    }

    /**
     * @param a the user that the administrative user wants to check the transaction limit
     * set the user.ClientUser a account frozen a has exceeded the week transaction limit
     */
    public boolean tradeLimit(ClientUser a){
        if(!a.getIsFrozen()){
            a.setFrozen(tm.getTradeNumber(a) > a.getWeekTransactionLimit());
            return true;
        }
        return false;
    }
    public void setDiff(ClientUser user,int diff) {
        user.setDiff(diff);
    }

    public void setWeekTransactionLimit(ClientUser user,int weekTransaction){
        user.setWeekTransactionLimit(weekTransaction);
    }
    public void setIncompleteTransaction(ClientUser user,int incompleteTransaction) {
       user.setIncompleteTransaction(incompleteTransaction);
    }



}
