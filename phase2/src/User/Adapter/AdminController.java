package User.Adapter;

import Trade.TradeManager;
import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

public class AdminController extends ClientUserController {
    private final AdminActivityManager am;


    public AdminController(UserManager um, ItemApprovalManager iam,
                           AdminActivityManager am, TradeManager tm) {
        super(um, iam, tm);
        this.am = am;

    }

    public AdminActivityManager getAm() {
        return am;
    }


    public void checkFrozen(String username){
        ClientUser user = (ClientUser) am.userAccess.getObject(username);
        if(um.readDiff(user)>=um.getDiff(user)){
            System.out.println("You have been freeze due to exceed difference between borrow and lend");
            am.setFreeze(username,true);
        }
        else if(am.incompleteTransaction(user)){
            System.out.println("You have been freeze due to maximum incomplete transaction");
            am.setFreeze(username,true);
        }
        else if(am.tradeLimit(username)){
            System.out.println("You have been freeze due to maximum trade limit");
            am.setFreeze(username,true);
        }
    }

    public void createAdmin(ClientUser user, String name, String password) {
        am.addNewAdmin(name, password);
    }

}
