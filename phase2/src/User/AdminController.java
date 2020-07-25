package User;

import Trade.TradeManager;

import java.io.File;

public class AdminController extends ClientUserController{
    private final AdminActivityManager am;
    public AdminController(ClientUser clientUser, UserManager um, ItemApprovalManager iam,
                           AdminActivityManager am, TradeManager tm) {
        super(clientUser, um, iam, tm);
        this.am = am;

    }

    public AdminActivityManager getAm() {
        return am;
    }

    public boolean getIsAdmin(){return user.getIsAdmin();}

    public void checkFrozen(ClientUser user){
        if(um.readDiff(user)>=um.getDiff(user)){
            System.out.println("You have been freeze due to exceed difference between borrow and lend");
            am.setFreeze(user,true);
        }
        else if(am.incompleteTransaction(user)){
            System.out.println("You have been freeze due to maximum incomplete transaction");
            am.setFreeze(user,true);
        }
        else if(am.tradeLimit(user)){
            System.out.println("You have been freeze due to maximum trade limit");
            am.setFreeze(user,true);
        }
    }

    public void createAdmin(String name, String password) {
        am.addNewAdmin(user, name, password);
    }

}
