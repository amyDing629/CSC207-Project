package User.Adapter;

import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;

import java.util.List;
/**
 * [Controller]
 * controllers that control admin
 */
public class AdminController extends ClientUserController implements IUserController, IAdminController{
    private final AdminActivityManager am;


    /**
     * [Constructor]
     */
    public AdminController() {
        super();
        this.am = new AdminActivityManager();

    }

    /**
     * @param username the name of user
     * set the difference between lend amount and borrow amount standard
     */
    public void setDiff(String username,int diff) {
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setDiff(diff);
        um.addUser(user);
    }

    /**
     * @param name the name of user
     * @param  password the password of user
     * create the new admin
     */
    public void createAdmin(String name, String password) {
        am.addNewAdmin(name, password);
    }

    /**
     * @param a name of the user
     * @param s freeze status
     * set the user status frozen
     */
    public void setFreeze(String a,boolean s){
        am.setFreeze(a, s);
    }

    /**
     * @param username name of the user
     * @param incompleteTransaction number of incomplete transaction
     * set the number of incomplete transaction
     */
    public void setIncompleteTransaction(String username,int incompleteTransaction) {
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setIncompleteTransaction(incompleteTransaction);
        um.addUser(user);
    }

    /**
     * @param username name of the user
     * @param weekTransaction number of week transaction
     * set the number of week transaction
     */
    public void setWeekTransactionLimit(String username, int weekTransaction){
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setWeekTransactionLimit(weekTransaction);
        um.addUser(user);
    }

    /**
     * @param exStandard the exchange standard
     * set the number of exchange standard
     */
    public void setExchangeStandard(int exStandard) {
        List<ClientUser> userList = um.getUserList();
        for (ClientUser user: userList) {
            ClientUser u = um.popUser(user.getId());
            u.setExStandard(exStandard);
            System.out.println("acb ex" + u.getUsername() + ": " + u.getExStandard());
            um.addUser(u);
            System.out.println("ac ex" + user.getUsername() + ": " + um.getUser(user.getId()).getExStandard());
        }
    }


}
