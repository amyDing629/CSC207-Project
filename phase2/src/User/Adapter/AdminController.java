package User.Adapter;

import Trade.TradeManager;
import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import java.io.FileNotFoundException;

public class AdminController extends ClientUserController {
    private final AdminActivityManager am;


    public AdminController() {
        super();
        this.am = new AdminActivityManager();

    }

    public AdminActivityManager getAm() {
        return am;
    }


    public void checkFrozen(String username) throws FileNotFoundException {
        if(um.readDiff(username)>=um.getDiff(username)){
            System.out.println("You have been freeze due to exceed difference between borrow and lend");
            am.setFreeze(username,true);
        }
        else if(am.incompleteTransaction(um.nameToUUID(username))){
            System.out.println("You have been freeze due to maximum incomplete transaction");
            am.setFreeze(username,true);
        }
        else if(am.tradeLimit(username)){
            System.out.println("You have been freeze due to maximum trade limit");
            am.setFreeze(username,true);
        }
    }

    public void createAdmin(ClientUser user, String name, String password) throws FileNotFoundException {
        am.addNewAdmin(name, password);
    }

}
