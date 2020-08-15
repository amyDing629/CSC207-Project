package User.Adapter;

import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;

import java.io.FileNotFoundException;
import java.util.List;

public class AdminController extends ClientUserController implements IUserController, IAdminController{
    private final AdminActivityManager am;


    public AdminController() {
        super();
        this.am = new AdminActivityManager();

    }

    public AdminActivityManager getAm() {
        return am;
    }


    public void checkFrozen(String username) {
        if(um.readDiff(username)>um.getDiff(username)){
            System.out.println("You have been freeze due to exceed difference between borrow and lend");
            ClientUser u = um.popUser(um.nameToUUID(username));
            u.setFrozen(true);
            um.addUser(u);
        }
        else if(am.incompleteTransaction(um.nameToUUID(username))){
            System.out.println("You have been freeze due to maximum incomplete transaction");
        }
        else if(am.tradeLimit(um.nameToUUID(username))){
            System.out.println("You have been freeze due to maximum trade limit");
        }
    }

    public void setDiff(String username,int diff) {
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setDiff(diff);
        um.addUser(user);
    }

    public void createAdmin(String name, String password) {
        am.addNewAdmin(name, password);
    }

    public void setFreeze(String a,boolean s){
        am.setFreeze(a, s);
    }

    public void setIncompleteTransaction(String username,int incompleteTransaction) {
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setIncompleteTransaction(incompleteTransaction);
        um.addUser(user);
    }

    public void setWeekTransactionLimit(String username, int weekTransaction){
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setWeekTransactionLimit(weekTransaction);
        um.addUser(user);
    }

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
